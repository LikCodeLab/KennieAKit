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

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;

import java.util.concurrent.BlockingQueue;

/**
 * 为执行网络请求分类的请求提供一个线程.
 * Provides a thread for performing network dispatch from a queue of requests.
 * <p>
 * 添加到指定队列的request 被 {@link Network} 接口通过网络处理.
 * 响应信息被提交到了缓存中存储, 使用的是 {@link Cache} 接口.
 * 有效的响应信息和错误信息都会被 {@link ResponseDelivery} 回传.
 * Requests added to the specified queue are processed from the network via a
 * specified {@link Network} interface. Responses are committed to cache, if
 * eligible, using a specified {@link Cache} interface. Valid responses and
 * errors are posted back to the caller via a {@link ResponseDelivery}.
 */
public class NetworkDispatcher extends Thread {
    /**
     * 存储request的队列
     * The queue of requests to service.
     */
    private final BlockingQueue<Request<?>> mQueue;
    /**
     * 操作网络的接口
     * The network interface for processing requests.
     */
    private final Network mNetwork;
    /**
     * 操作缓存的接口
     * The cache to write to.
     */
    private final Cache mCache;
    /**
     * 响应回传工具
     * For posting responses and errors.
     */
    private final ResponseDelivery mDelivery;
    /**
     * 退出标记
     * Used for telling us to die.
     */
    private volatile boolean mQuit = false;

    /**
     * 创建一个新的网络分流调度线程.只有调用了 {@link #start()} 方法才会按照顺序处理请求.
     * Creates a new network dispatcher thread.  You must call {@link #start()}
     * in order to begin processing.
     *
     * @param queue    Queue of incoming requests for triage 处理队列
     * @param network  Network interface to use for performing requests 处理request的网络接口
     * @param cache    Cache interface to use for writing responses to cache 缓存接口
     * @param delivery Delivery interface to use for posting responses 响应回传器
     */
    public NetworkDispatcher(BlockingQueue<Request<?>> queue,
                             Network network, Cache cache,
                             ResponseDelivery delivery) {
        mQueue = queue;
        mNetwork = network;
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

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void addTrafficStatsTag(Request<?> request) {
        // Tag the request (if API >= 14)
        // 目标API >= 14
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            /**
             * 流量状态查询
             * 使用TrafficStats.setThreadStatsTag()方法来标记线程内部发生的数据传输情况
             * Apache的HttpClient和URLConnection类库会基于当前的getThreadStatsTag()方法的返回值来自动的标记网络套接字。这些类库也可以通过活动的保持池（keep-alive pools）标记网络套接字，并在回收时解除标记。
             * 网络套接字标记在Android4.0以后被支持，但是实时的统计结果只会被显示在运行Android4.0.3以后的设备上。
             * @see http://www.2cto.com/kf/201312/262627.html
             */
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    @Override
    public void run() {
        // 线程优先级设置
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while (true) {
            // 统计执行时间
            long startTimeMs = SystemClock.elapsedRealtime();
            Request<?> request;
            // 释放先前的请求对象来避免请求对象的泄漏
            try {
                // 从网络队列中取出一个request.
                // Take a request from the queue.
                // 获取request对象
                request = mQueue.take();
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

            try {
                request.addMarker("network-queue-take");

                // If the request was cancelled already, do not perform the
                // network request.
                // 如果请求被取消，请不要打扰它 .
                if (request.isCanceled()) {
                    request.finish("network-discard-cancelled");
                    continue;
                }

                // 为该线程统计流量
                addTrafficStatsTag(request);

                // Perform the network request.
                // 执行网络请求.
                NetworkResponse networkResponse = mNetwork.performRequest(request);
                request.addMarker("network-http-complete");

                // 服务器返回 304 并且我们已经回传了一个响应,
                // 搞定 -- 不需要发送一个相同的响应.
                // If the server returned 304 AND we delivered a response already,
                // we're done -- don't deliver a second identical response.
                if (networkResponse.notModified && request.hasHadResponseDelivered()) {
                    request.finish("not-modified");
                    continue;
                }

                // 解析响应消息.
                // Parse the response here on the worker thread.
                Response<?> response = request.parseNetworkResponse(networkResponse);
                request.addMarker("network-parse-complete");

                // Write to cache if applicable.
                // 写入到cache中.
                // TODO: 304 系列的响应信息只需要更新元数据而不需要更新内容.
                // TODO: Only update cache metadata instead of entire record for 304s.
                if (request.shouldCache() && response.cacheEntry != null) {
                    mCache.put(request.getCacheKey(), response.cacheEntry);
                    request.addMarker("network-cache-written");
                }

                // 回传响应信息.
                // Post the response back.
                request.markDelivered();
                mDelivery.postResponse(request, response);
            } catch (VolleyError volleyError) {
                volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                parseAndDeliverNetworkError(request, volleyError);
            } catch (Exception e) {
                VolleyLog.e(e, "Unhandled exception %s", e.toString());
                VolleyError volleyError = new VolleyError(e);
                volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                mDelivery.postError(request, volleyError);
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError error) {
        error = request.parseNetworkError(error);
        mDelivery.postError(request, error);
    }
}
