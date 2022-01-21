package com.want.base.http.volley;

import com.android.volley.Response;
import com.want.base.http.HttpListener;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
class MResponseListener implements Response.Listener<String> {

    private HttpListener<String> mListener;

    MResponseListener(HttpListener<String> listener) {
        this.mListener = listener;
    }

    /**
     * Called when a response is received.
     *
     * @param response
     */
    @Override
    public void onResponse(String response) {
        mListener.onSuccess(response);

        // call the finish
        mListener.onFinish();
    }
}
