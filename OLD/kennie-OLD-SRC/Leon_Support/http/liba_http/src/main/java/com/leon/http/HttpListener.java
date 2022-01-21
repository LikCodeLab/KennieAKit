package com.leon.http;


import com.leon.http.error.HttpError;

/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * Http 请求回调接口
 * <br>
 */
public interface HttpListener<T> {


    /**
     * 服务器响应请求
     *
     * @param response {@link HttpResponse}
     */
    void onResponse(HttpResponse response);

    /**
     * 服务器响应成功
     *
     * @param result 响应的理想数据。
     */
    void onSuccess(T result);

    /**
     * 网络交互过程中发生错误
     *
     * @param error {@link HttpError}
     */
    void onError(HttpError error);

    /**
     * 网络交互结束
     */
    void onFinish();

}
