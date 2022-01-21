package com.want.base.sdk.model.crash;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.want.base.sdk.utils.PhoneUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class BaseCrashhandler implements ICrashHandler, UncaughtExceptionHandler {
    private static final String ERROR_FORMATER = "[androidcrash] [%s] [%s], message: %s; stacktrace: %s.";


    private Context mContext;

    public BaseCrashhandler(Context context) {
        this.mContext = context;
    }

    protected Context getContext() {
        return this.mContext;
    }

    /**
     * Handle the uncaught exception.
     *
     * @param thread          {@link Thread}
     * @param ex              {@link Throwable}
     * @param mainthread      wheather the main thread
     * @param formatedMessage formated message
     */
    protected abstract void handleUncaughtException(Thread thread,
                                                    Throwable ex,
                                                    boolean mainthread,
                                                    String formatedMessage);

    /**
     * Show the alert dialog
     *
     * @param context
     */
    protected void showAlertDialog(Context context) {
        CrashDialogActivity.launch(context);
    }

    /**
     * The thread is being terminated by an uncaught exception. Further
     * exceptions thrown in this method are prevent the remainder of the
     * method from executing, but are otherwise ignored.
     *
     * @param thread the thread that has an uncaught exception
     * @param ex     the exception that was thrown
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // TODO: 15/10/19 make deviceinfo
        final boolean isMainThread = isMainThread();
        final StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        writer.flush();

        StringBuilder builder = new StringBuilder();
        builder.append("[thread name]: ");
        builder.append(thread.getName());
        builder.append("; \n");
        builder.append("[mainthread]: ");
        builder.append(isMainThread);
        builder.append("; \n");
        builder.append("[message]: ");
        builder.append(ex.getMessage());
        builder.append("; \n");
        builder.append("[stacktrace]: ");
        builder.append(writer.toString());

        if (!isMainThread) {
            showAlertDialog(getContext());
        }

        final String reportMessage = builder.toString();
        Log.e("[crash]", reportMessage);
        handleUncaughtException(thread, ex, isMainThread, reportMessage);
    }

    /**
     * Wheather current thread is the main thread.
     *
     * @return true, main thread; false, otherwise.
     */
    protected boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Make the device info
     *
     * @param context context
     *
     * @return
     */
    protected String getDeviceInfo(Context context) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[model] ");
        buffer.append(PhoneUtils.getModel(context));
        buffer.append("; ");

        buffer.append("[brand] ");
        buffer.append(PhoneUtils.getBrand(context));
        buffer.append("; ");

        buffer.append("[memoryinfo] avil: ");
        final String[] memoryinfo = PhoneUtils.getMemoryInfo(context);
        buffer.append(memoryinfo[0]);
        buffer.append(", total: ");
        buffer.append(memoryinfo[1]);
        buffer.append("; ");

        buffer.append("[screeninfo] width: ");
        buffer.append(PhoneUtils.getScreenWidth(context));
        buffer.append(", height: ");
        buffer.append(PhoneUtils.getScreenHeight(context));
        buffer.append("; ");


        return buffer.toString();
    }
}
