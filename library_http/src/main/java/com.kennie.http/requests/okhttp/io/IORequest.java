package com.kennie.http.requests.okhttp.io;

import static com.kennie.http.log.HttpLoggingInterceptor.IO_FLAG_HEADER;

import android.text.TextUtils;

import com.kennie.http.api.Api;
import com.kennie.http.callbacks.TransmitCallback;
import com.kennie.http.requests.Request;
import com.kennie.http.utils.CastUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;



public abstract class IORequest<R extends IORequest<R>> extends Request<R> {

    // http api,兼容 rxJava 观察者模式，需要返回观察对象时，将请求转换成 Retrofit 去请求
    protected Api mApi;
    protected String url = "";
    // request tag
    protected Object tag;
    protected Map<String, String> localParams = new LinkedHashMap<>();//请求参数

    /**
     * set request‘s tag，use to manage the request
     *
     * @param tag request's tag
     * @return Request Object
     */
    public R tag(@NonNull Object tag) {
        this.tag = tag;
        return CastUtils.cast(this);
    }

    public IORequest(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
        }
        clientBuilder = null;
        addHeader(IO_FLAG_HEADER, "IORequest ignore print log !!!");
    }

    protected abstract <T extends TransmitCallback> void execute(T callback);

    /**
     * 添加请求参数
     *
     * @param paramKey
     * @param paramValue
     * @return
     */
    public R addParam(String paramKey, String paramValue) {
        if (paramKey != null && paramValue != null) {
            this.localParams.put(paramKey, paramValue);
        }
        return CastUtils.cast(this);
    }

    /**
     * 添加请求参数
     *
     * @param params
     * @return
     */
    public R addParams(Map<String, String> params) {
        if (params != null) {
            this.localParams.putAll(params);
        }
        return CastUtils.cast(this);
    }

    /**
     * 移除请求参数
     *
     * @param paramKey
     * @return
     */
    public R removeParam(String paramKey) {
        if (paramKey != null) {
            this.localParams.remove(paramKey);
        }
        return CastUtils.cast(this);
    }

    /**
     * 设置请求参数
     *
     * @param params
     * @return
     */
    public R params(Map<String, String> params) {
        if (params != null) {
            this.localParams = params;
        }
        return CastUtils.cast(this);
    }

    @Override
    protected void injectLocalParams() {
        super.injectLocalParams();
        if (getGlobalConfig().getGlobalParams() != null) {
            localParams.putAll(getGlobalConfig().getGlobalParams());
        }
        mApi = getCommonRetrofit().create(Api.class);
    }
}
