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

import java.util.Collections;
import java.util.Map;

/**
 * 该接口用于以键值对的形式缓存byte数组,键是一个String
 * An interface for a cache keyed by a String with a byte array as data.
 */
public interface Cache {
    /**
     * 返回一个空的Entry.
     * Retrieves an entry from the cache.
     *
     * @param key Cache key
     * @return An {@link Entry} or null in the event of a cache miss
     */
    public Entry get(String key);

    /**
     * 添加或者替换entry到cache.
     * Adds or replaces an entry to the cache.
     *
     * @param key   Cache key
     * @param entry Data to store and metadata for cache coherency, TTL, etc. 要存储的Entry
     */
    public void put(String key, Entry entry);

    /**
     * 执行任何具有潜在长期运行特性的动作之前,必须进行初始化;
     * 该方法运行在子线程.
     * Performs any potentially long-running actions needed to initialize the cache;
     * will be called from a worker thread.
     */
    public void initialize();

    /**
     * 刷新Cache中的Entry.
     * Invalidates an entry in the cache.
     *
     * @param key        Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire 该Entry是否完整过期
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * 从cache中移除一个entry.
     * Removes an entry from the cache.
     *
     * @param key Cache key
     */
    public void remove(String key);

    /**
     * 清空cache.
     * Empties the cache.
     */
    public void clear();

    /**
     * 一个entry的数据和主数据实体bean.
     * Data and metadata for an entry returned by the cache.
     */
    public static class Entry {
        /**
         * 数据区域.
         * The data returned from cache.
         */
        public byte[] data;

        /**
         * cache数据的Etag.
         * ETag for cache coherency.
         */
        public String etag;

        /**
         * 服务器响应的时间.
         * Date of this response as reported by the server.
         */
        public long serverDate;

        /**
         * 资源最后编辑时间.
         * The last modified date for the requested object.
         */
        public long lastModified;

        /**
         * record 的ttl.
         * TTL for this record.
         */
        public long ttl;

        /**
         * record 的软ttl.
         * Soft TTL for this record.
         */
        public long softTtl;

        /**
         * 从服务器接收的固定的响应头; 不能为null.
         * Immutable response headers as received from server; must be non-null.
         */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /**
         *  判断该资源是否超时.
         * True if the entry is expired.
         */
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        /**
         * 返回True如果需要从原数据上刷新.
         * True if a refresh is needed from the original data source.
         */
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

}
