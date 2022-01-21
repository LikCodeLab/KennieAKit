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

/**
 * Request的默认重试策略.
 * Default retry policy for requests.
 */
public class DefaultRetryPolicy implements RetryPolicy {
    /**
     * 当前的超时毫秒数.
     * The current timeout in milliseconds.
     */
    private int mCurrentTimeoutMs;
    /**
     * 当前的重试次数.
     * The current retry count.
     */
    private int mCurrentRetryCount;
    /**
     * 最大重试次数.
     * The maximum number of attempts.
     */
    private final int mMaxNumRetries;
    /**
     * 策略乘法器.
     * The backoff multiplier for the policy.
     */
    private final float mBackoffMultiplier;

    /**
     * 默认的Socket超时毫秒数
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 2500;


    /**
     * 默认的重试次数，重试1次
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /**
     * 默认的乘法器
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    /**
     * 使用默认的超时时间构造一个实例.
     * Constructs a new retry policy using the default timeouts.
     */
    public DefaultRetryPolicy() {
        this(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
    }

    /**
     * 构造一个新的重试策略.
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy. 初始化超时时间.
     * @param maxNumRetries     The maximum number of retries. 最大的重试次数.
     * @param backoffMultiplier Backoff multiplier for the policy. 乘法器.
     */
    public DefaultRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        mCurrentTimeoutMs = initialTimeoutMs;
        mMaxNumRetries = maxNumRetries;
        mBackoffMultiplier = backoffMultiplier;
    }

    /**
     * 返回当前超时时间.
     * Returns the current timeout.
     */
    @Override
    public int getCurrentTimeout() {
        return mCurrentTimeoutMs;
    }

    /**
     * 返回当前重试次数.
     * Returns the current retry count.
     */
    @Override
    public int getCurrentRetryCount() {
        return mCurrentRetryCount;
    }

    /**
     * 返回后退乘法器.
     * Returns the backoff multiplier for the policy.
     */
    public float getBackoffMultiplier() {
        return mBackoffMultiplier;
    }

    /**
     * 准备下一次重试.
     * Prepares for the next retry by applying a backoff to the timeout.
     *
     * @param error The error code of the last attempt. 上次重试的返回码.
     */
    @Override
    public void retry(VolleyError error) throws VolleyError {
        mCurrentRetryCount++;
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackoffMultiplier);
        if (!hasAttemptRemaining()) {
            throw error;
        }
    }

    /**
     * 返回还没有没有重试次数.
     * Returns true if this policy has attempts remaining, false otherwise.
     */
    protected boolean hasAttemptRemaining() {
        return mCurrentRetryCount <= mMaxNumRetries;
    }
}
