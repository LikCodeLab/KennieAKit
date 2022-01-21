/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import android.os.Process;

import java.util.concurrent.BlockingQueue;

/**
 * 为执行高速缓存分类的请求提供一个线程.
 * <p>
 * Provides a thread for performing cache triage on a queue of requests.
 * <p>
 * 添加到指定的缓存队列的请求从缓存解析.
 * 任何可以返回的响应信息都会通过 {@link ResponseDelivery} 返回给调用者.
 * 丢失的缓存或者是要求刷新的响应会被插入到指定的网络队列，它将会被 {@link NetworkDispatcher} 处理.
 * Requests added to the specified cache queue are resolved from cache.
 * Any deliverable response is posted back to the caller via a
 * {@link ResponseDelivery}.  Cache misses and responses that require
 * refresh are enqueued on the specified network queue for processing
 * by a {@link NetworkDispatcher}.
 */
public class CacheDispatcher extends Thread {

    private static final boolean DEBUG = VolleyLog.DEBUG;

    /**
     * 要执行的缓存队列.
     * The queue of requests coming in for triage.
     */
    private final BlockingQueue<Request<?>> mCacheQueue;

    /**
     * 缓存队列.
     * The queue of requests going out to the network.
     */
    private final BlockingQueue<Request<?>> mNetworkQueue;

    /**
     * 要读取的缓存对象.
     * The cache to read from.
     */
    private final Cache mCache;

    /**
     * 传递响应的Delivery.
     * For posting responses.
     */
    private final ResponseDelivery mDelivery;

    /**
     * 是否取消任务.
     * Used for telling us to die.
     */
    private volatile boolean mQuit = false;

    /**
     * 创建一个新的缓存分流调度线程 .  只有调用了 {@link #start()} 方法才会按照顺序处理请求.
     * Creates a new cache triage dispatcher thread.  You must call {@link #start()}
     * in order to begin processing.
     *
     * @param cacheQueue   Queue of incoming requests for triage 缓存队列
     * @param networkQueue Queue to post requests that require network to 网络队列
     * @param cache        Cache interface to use for resolution 要使用的Cache
     * @param delivery     Delivery interface to use for posting responses 响应回传工具
     */
    public CacheDispatcher(
            BlockingQueue<Request<?>> cacheQueue, BlockingQueue<Request<?>> networkQueue,
            Cache cache, ResponseDelivery delivery) {
        mCacheQueue = cacheQueue;
        mNetworkQueue = networkQueue;
        mCache = cache;
        mDelivery = delivery;
    }

    /**
     * 强制性立即停止调度器.  如果在队列中还有其他请求, 它们不能保证被处理 .
     * Forces this dispatcher to quit immediately.  If any requests are still in
     * the queue, they are not guaranteed to be processed.
     */
    public void quit() {
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        //开启一个新的调度器
        if (DEBUG) VolleyLog.v("start new dispatcher");

        // 设置线程优先级，THREAD_PRIORITY_BACKGROUND:后台线程
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

        // Make a blocking call to initialize the cache.
        // 缓存初始化，会遍历整个缓存文件夹
        // 初始化缓存对象.
        // 在子线程内部调用
        mCache.initialize();

        while (true) {
            try {

                // Get a request from the cache triage queue, blocking until
                // at least one is available.
                // 从缓存队列中取出一个request.
                final Request<?> request = mCacheQueue.take();
                // 标记该request已经被取出
                request.addMarker("cache-queue-take");

                // If the request has been canceled, don't bother dispatching it.
                // 如果请求被取消，请不要打扰它 .
                if (request.isCanceled()) {
                    request.finish("cache-discard-canceled");
                    continue;
                }

                // Attempt to retrieve this item from cache.
                // 试图从缓存中检索这个项目 .
                Cache.Entry entry = mCache.get(request.getCacheKey());
                if (entry == null) {
                    request.addMarker("cache-miss");
                    // 缓存中找不到；发送到网络调度程序 .
                    // Cache miss; send off to the network dispatcher.
                    mNetworkQueue.put(request);
                    continue;
                }

                // 缓存已经过期，发送到网络调度程序.
                // If it is completely expired, just send it to the network.
                if (entry.isExpired()) {
                    request.addMarker("cache-hit-expired");
                    request.setCacheEntry(entry);
                    mNetworkQueue.put(request);
                    continue;
                }

                // 找到对应的缓存; 解析数据返回给 request.
                // We have a cache hit; parse its data for delivery back to the request.
                request.addMarker("cache-hit");
                Response<?> response = request.parseNetworkResponse(
                        new NetworkResponse(entry.data, entry.responseHeaders));
                request.addMarker("cache-hit-parsed");

                // 缓存数据需不需要刷新
                if (!entry.refreshNeeded()) {
                    // 缓存确实没有过期. 直接传递回去.
                    // Completely unexpired cache hit. Just deliver the response.
                    mDelivery.postResponse(request, response);
                } else {
                    // 软超时缓存. 我们可以把数据返回,同时也需要访问网络来刷新该缓存资源.
                    // Soft-expired cache hit. We can deliver the cached response,
                    // but we need to also send the request to the network for
                    // refreshing.
                    request.addMarker("cache-hit-refresh-needed");
                    request.setCacheEntry(entry);

                    // 打上这个,标识该 response 用的是软超时的缓存，需要刷新.
                    // Mark the response as intermediate.
                    response.intermediate = true;

                    // 把数据立即传递给用户 然后我们需要立即请求网络获取新的资源.
                    // Post the intermediate response back to the user and have
                    // the delivery then forward the request along to the network.
                    mDelivery.postResponse(request, response, new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // 把请求放进网络队列中
                                mNetworkQueue.put(request);
                            } catch (InterruptedException e) {
                                // Not much we can do about this.
                                // 除此之外我们做不了别的了.
                            }
                        }
                    });
                }

            } catch (InterruptedException e) {
                // 取出request发生中断
                // 如果外部要求退出循环，在这里return退出无限循环.
                // We may have been interrupted because it was time to quit.
                if (mQuit) {
                    return;
                }
                // 否则继续向下进行下一个请求
                continue;
            }
        }
    }
}
