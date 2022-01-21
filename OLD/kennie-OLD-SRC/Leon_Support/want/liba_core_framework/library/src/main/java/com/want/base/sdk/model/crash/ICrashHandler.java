package com.want.base.sdk.model.crash;

import android.content.Context;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Android Crash Handler。
 * <br>
 */
public interface ICrashHandler {

    /**
     * Report error.
     *
     * @param context context
     * @param e       {@link Throwable}
     * @param error   message
     */
    void reportError(Context context, Throwable e, String error);

}
