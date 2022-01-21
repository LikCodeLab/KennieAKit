package com.want.base.sdk.model.crash;

import android.content.Context;
import android.os.Process;

import com.umeng.analytics.MobclickAgent;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class UmengCrashHandler extends BaseCrashhandler {

    public UmengCrashHandler(Context context) {
        super(context);
//        MobclickAgent.setCatchUncaughtExceptions(false);
    }

    /**
     * Handle the uncaught exception.
     *
     * @param thread          {@link Thread}
     * @param ex              {@link Throwable}
     * @param formatedMessage formated message
     */
    @Override
    protected void handleUncaughtException(Thread thread,
                                           Throwable ex,
                                           boolean mainthread,
                                           String formatedMessage) {
        final Context context = getContext();
        // report log information
        if (AndroidCrashHandler.REPORTLOG) {
            reportError(context, ex, formatedMessage);
        }

        if (mainthread) {
            MobclickAgent.onKillProcess(context);
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * Report error.
     *
     * @param context context
     * @param e       {@link Throwable}
     * @param error   message
     */
    @Override
    public void reportError(Context context, Throwable e, String error) {
        MobclickAgent.reportError(context, error);
    }
}
