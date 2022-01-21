package com.want.base.http;

import com.android.volley.RequestQueue;

import java.io.File;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Http 请求抽象
 * <br>
 */

//qiangzeng160930增加取消网络请求的函数
public interface HttpClient<T> {


    /**
     * 执行网络请求
     *
     * @param request
     * @param listener
     */
    void request(HttpRequest request, final HttpListener<T> listener);

    /**
     * 下载文件
     *
     * @param request  {@link HttpRequest}
     * @param filePath filePath
     * @param listener listener
     */
    void download(HttpRequest request, File filePath, final HttpListener<T> listener);

    /**
     * Cancels all requests in this queue for which the given filter applies.
     * @param filter The filtering function to use
     */
    public void cancelAll(RequestQueue.RequestFilter filter);

    /**
     ** Cancels all requests in this queue with the given tag. Tag must be non-null
     * and equality is by identity.
     */
    public void cancelAll(final Object tag);

}
