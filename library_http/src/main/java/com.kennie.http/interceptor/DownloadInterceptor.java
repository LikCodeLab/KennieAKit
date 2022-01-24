package com.kennie.http.interceptor;

import com.kennie.http.callbacks.TransmitCallback;
import com.kennie.http.models.ProgressDownloadBody;

import java.io.IOException;
import io.reactivex.rxjava3.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * <p>
 * Description :下载拦截器
 */
public class DownloadInterceptor implements Interceptor {

    private final TransmitCallback mCallback;

    public DownloadInterceptor(TransmitCallback callback) {
        mCallback = callback;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ProgressDownloadBody responseBody = new ProgressDownloadBody(response.body(), mCallback);
        return response.newBuilder()
                .body(responseBody)
                .build();
    }
}
