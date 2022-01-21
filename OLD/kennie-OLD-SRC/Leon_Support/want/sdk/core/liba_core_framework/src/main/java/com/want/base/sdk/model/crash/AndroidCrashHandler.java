package com.want.base.sdk.model.crash;

import android.content.Context;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class AndroidCrashHandler {

    public static boolean REPORTLOG = true;

    private AndroidCrashHandler() {
        // hide
    }

    public static void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(new UmengCrashHandler(context));
    }

}
