package com.want.base.http.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.want.base.http.HttpClient;
import com.want.base.http.HttpListener;
import com.want.base.http.HttpRequest;

import java.io.File;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Volley 的 HttpClient 实现
 * <br>
 */

//qiangzeng160930增加取消网络请求的函数
public class VolleyHttpClientImpl implements HttpClient<String> {

    private static VolleyHttpClientImpl INSTANCE;
    private static final int[] sLock = new int[0];

    private HttpStack mHttpStack;
    private RequestQueue mRequestQueue;


    private VolleyHttpClientImpl(Context context) {
        mHttpStack = new OkHttpStack();
        mRequestQueue = Volley.newRequestQueue(context, mHttpStack);
    }

    public static VolleyHttpClientImpl getInstance(Context context) {
        if (null == INSTANCE) {
            synchronized (sLock) {
                if (null == INSTANCE) {
                    INSTANCE = new VolleyHttpClientImpl(context);
                }
            }
        }
        return INSTANCE;
    }



    /**qiangzeng160930增加请求队列tag
     * 执行网络请求
     *
     * @param request
     * @param listener
     */
    @Override
    public void request(HttpRequest request, HttpListener<String> listener) {
        final MStringRequest mRequest = new MStringRequest(request, listener);

        mRequest.setTag("POST");

        mRequestQueue.add(mRequest);
    }

    /**qiangzeng160930增加请求队列tag
     * 下载文件
     *
     * @param request  {@link HttpRequest}
     * @param filePath filePath
     * @param listener listener
     */
    @Override
    public void download(HttpRequest request, File filePath, HttpListener<String> listener) {
        final DownloadRequest downloadRequest = new DownloadRequest(request, filePath, listener);

        downloadRequest.setTag("Download");

        mRequestQueue.add(downloadRequest);
    }

    /**
     * Cancels all requests in this queue for which the given filter applies.
     * @param filter The filtering function to use
     */
    @Override
    public void cancelAll(RequestQueue.RequestFilter filter) {
        mRequestQueue.cancelAll(filter);
    }

    /**
     * Cancels all requests in this queue with the given tag. Tag must be non-null
     * and equality is by identity.
     */
    @Override
    public void cancelAll(final Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
