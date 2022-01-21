package com.leon.http.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.leon.http.HttpListener;
import com.leon.http.error.HttpAuthFailureError;
import com.leon.http.error.HttpNetworkError;
import com.leon.http.error.HttpNoConnectionError;
import com.leon.http.error.HttpParseError;
import com.leon.http.error.HttpServerError;
import com.leon.http.error.HttpTimeOutError;



/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b> <br>
 */
class MErrorListener implements Response.ErrorListener {

    private HttpListener mListener;

    MErrorListener(HttpListener listener) {
        this.mListener = listener;
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        if (null == error) {
            mListener.onError(null);
            return;
        }

        if (error instanceof AuthFailureError) {
            mListener.onError(new HttpAuthFailureError(((AuthFailureError) error).getResolutionIntent()));
        } else if (error instanceof ServerError) {
            mListener.onError(new HttpServerError());
        } else if (error instanceof NetworkError) {
            mListener.onError(new HttpNetworkError());
        } else if (error instanceof NoConnectionError) {
            mListener.onError(new HttpNoConnectionError());
        } else if (error instanceof ParseError) {
            mListener.onError(new HttpParseError());
        } else if (error instanceof TimeoutError) {
            mListener.onError(new HttpTimeOutError());
        } else {
            mListener.onError(null);
        }

        // call the finish
        mListener.onFinish();
    }
}
