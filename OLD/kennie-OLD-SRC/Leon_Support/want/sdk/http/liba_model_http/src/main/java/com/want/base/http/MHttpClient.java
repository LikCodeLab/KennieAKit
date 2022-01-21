package com.want.base.http;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.want.base.http.volley.VolleyHttpClientImpl;

import java.io.File;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 网络请求客户端
 * <br>
 */

//qiangzeng160930增加取消网络请求的函数
public class MHttpClient implements HttpClient<String> {

    private static MHttpClient INSTANCE;
    private static final int[] sLock = new int[0];

    private Context mContext;
    private HttpClient<String> mHttpClient;

    private static class SingletonHoler {
        private static final MHttpClient INSTANCE = new MHttpClient();
    }

    private MHttpClient() {
        // empty
    }

    private MHttpClient(Context context, HttpClient<String> httpClient) {
        this.mContext = context;
        this.mHttpClient = httpClient;
        if (null == mHttpClient) {
            this.mHttpClient = VolleyHttpClientImpl.getInstance(mContext);
        }
    }

    /**
     * {@link MHttpClient#getInstance(Context, HttpClient)}.
     *
     * @param context context
     *
     * @return
     */
    public static MHttpClient getInstance(Context context) {
        return getInstance(context, VolleyHttpClientImpl.getInstance(context));
    }

    /**
     * MMC 网络请求框架。 默认使用 VolleyHttpClientImpl
     *
     * @param context    Android's Application Context
     * @param httpClient HttpClient
     *
     * @return
     */
    public static MHttpClient getInstance(Context context, HttpClient<String> httpClient) {
        SingletonHoler.INSTANCE.mContext = context.getApplicationContext();
        SingletonHoler.INSTANCE.mHttpClient = httpClient;
        return SingletonHoler.INSTANCE;
    }

    public void loadImage(ImageView imageView, String url) {

    }

    /**
     * 执行网络请求
     *
     * @param request
     * @param listener
     */
    @Override
    public void request(HttpRequest request, HttpListener<String> listener) {
        mHttpClient.request(request, listener);
    }

    /**
     * 下载文件
     *
     * @param request  {@link HttpRequest}
     * @param filePath filePath
     * @param listener listener
     */
    @Override
    public void download(HttpRequest request, File filePath, HttpListener<String> listener) {
        mHttpClient.download(request, filePath, listener);
    }

    /**
     * Cancels all requests in this queue for which the given filter applies.
     * @param filter The filtering function to use
     */
    @Override
    public void cancelAll(RequestQueue.RequestFilter filter) {
        mHttpClient.cancelAll(filter);
    }

    /**
     * Cancels all requests in this queue with the given tag. Tag must be non-null
     * and equality is by identity.
     */
    @Override
    public void cancelAll(final Object tag) {
        mHttpClient.cancelAll(tag);
    }
}
