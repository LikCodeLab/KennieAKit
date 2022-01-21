package com.want.base.http.volley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.leon.log.lg;
import com.want.base.http.HttpDebug;
import com.want.base.http.HttpListener;
import com.want.base.http.HttpRequest;
import com.want.base.http.HttpResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class MStringRequest extends StringRequest {

    private HttpRequest mHttpRequest;
    private HttpListener<String> mHttpListener;


    public MStringRequest(HttpRequest request, HttpListener<String> listener) {
        super(request.getMethod().getValue()
                , (null != request.getParams() && Method.GET == request.getMethod().getValue())
                  ? makeurl(request.getUrl(), request.getParams()) : request.getUrl()
                , new MResponseListener(listener)
                , new MErrorListener(listener));
        this.mHttpRequest = request;
        this.mHttpListener = listener;
        if (HttpDebug.VERBOSE) {
            lg.v(HttpDebug.TAG, "request url: " + getUrl());
            lg.v(HttpDebug.TAG, "request method: " + request.getMethod());
        }
        RetryPolicy policy = new DefaultRetryPolicy(request.getTimeoutInMillions(),
                                                    request.getRetryCount(),
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(policy);
    }

    /**
     * Make the GET methon url.
     * <p/>
     * In Volley, GET method not call the getParams() method.
     *
     * @param url    The request url.
     * @param params The request params.
     *
     * @return The url with params.
     */
    private static String makeurl(String url, Map<String, String> params) {
        final int pSize = params.size();
        if (pSize == 0) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        for (String key : params.keySet()) {
            builder.append(key);
            builder.append("=");
            builder.append(params.get(key));
            builder.append("&");
        }

        String resutl = builder.toString();
        resutl = resutl.substring(0, resutl.length() - 1);
        if (HttpDebug.VERBOSE) {
            lg.v(HttpDebug.TAG, "request urlparams: " + resutl);
        }

        return resutl;
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        final Map<String, String> params = mHttpRequest.getParams();
        if (HttpDebug.VERBOSE) {
            lg.v(HttpDebug.TAG, "request params: " + lg.getString(params));
        }
        return params;
    }

    /**
     * Returns a list of extra HTTP headers to go along with this request. Can
     * throw {@link AuthFailureError} as authentication may be required to
     * provide these values.
     *
     * @throws AuthFailureError In the event of auth failure
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHttpRequest.getHeaders();
    }

    /**
     * Returns the content type of the POST or PUT body.
     */
    @Override
    public String getBodyContentType() {
        return mHttpRequest.getContentType();
    }

    /**
     * Returns the cache key for this request.  By default, this is the URL.
     */
    @Override
    public String getCacheKey() {
        if (mHttpRequest.getMethod() == HttpRequest.Builder.Method.GET) {
            return makeurl(getUrl(), mHttpRequest.getParams());
        } else {
            return super.getCacheKey();
        }


    }

    /**
     * Return the method for this request.  Can be one of the values in {@link Method}.
     */
    @Override
    public int getMethod() {
        return mHttpRequest.getMethod().getValue();
    }

    /**
     * Returns which encoding should be used when converting POST or PUT parameters returned by
     * {@link #getParams()} into a raw POST or PUT body.
     * <p/>
     * <p>This controls both encodings:
     * <ol>
     * <li>The string encoding used when converting parameter names and values into bytes prior
     * to URL encoding them.</li>
     * <li>The string encoding used when converting the URL encoded parameters into a raw
     * byte array.</li>
     * </ol>
     */
    @Override
    protected String getParamsEncoding() {
        return mHttpRequest.getParamsEncodeing();
    }


    /**
     * Returns the {@link Priority} of this request; {@link Priority#NORMAL} by default.
     */
    @Override
    public Priority getPriority() {
        return Priority.valueOf(mHttpRequest.getPriority().name());
    }

    /**
     * Returns the raw POST body to be sent.
     *
     * @throws AuthFailureError In the event of auth failure
     * @deprecated Use {@link #getBody()} instead.
     */
    @Override
    public byte[] getPostBody() throws AuthFailureError {
        return getBody();
    }

    /**
     * Returns the raw POST or PUT body to be sent.
     * <p/>
     * <p>By default, the body consists of the request parameters in
     * application/x-www-form-urlencoded format. When overriding this method, consider overriding
     * {@link #getBodyContentType()} as well to match the new body format.
     *
     * @throws AuthFailureError in the event of auth failure
     */
    @Override
    public byte[] getBody() throws AuthFailureError {
        byte[] bodyByt = null;

        final String body = mHttpRequest.getBody();
        if (!TextUtils.isEmpty(body)) {
            try {
                bodyByt = mHttpRequest.getBody().getBytes(mHttpRequest.getParamsEncodeing());
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                              body, mHttpRequest.getParamsEncodeing());
            }
        }

        if (null == bodyByt) {
            bodyByt = super.getBody();
        }

        if (HttpDebug.VERBOSE) {
            try {
                lg.v(HttpDebug.TAG,
                     "request body: " + new String(bodyByt, mHttpRequest.getParamsEncodeing()));
            } catch (UnsupportedEncodingException e) {
                // ignore
            }
        }

        return bodyByt;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        HttpResponse rsp = new HttpResponse(response.statusCode
                , response.data
                , response.headers
                , response.notModified
                , response.networkTimeMs);
        if (HttpDebug.VERBOSE) {
            lg.v(HttpDebug.TAG, "response status: " + rsp.statusCode);
            lg.v(HttpDebug.TAG, "response headers: " + lg.getString(rsp.headers));
            lg.v(HttpDebug.TAG, "response data: " + rsp.dataToString(rsp.data));
        }

        if (null != mHttpListener) {
            mHttpListener.onResponse(rsp);
        }
        return super.parseNetworkResponse(response);
    }
}
