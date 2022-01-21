package com.want.base.sdk.framework.app.core;

import android.content.Context;
import android.os.Handler;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/25<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface ISDKUIHelper {

    /**
     * Get a single instance handler
     *
     * @return {@link Handler}
     */
    Handler getHandler();

    /**
     * Run a {@link UIRunable} on the main thread.
     *
     * @param r {@link UIRunable}
     */
    void post(final UIRunable r);

    /**
     * Run a {@link UIRunable} on the main thread.
     *
     * @param r     {@link UIRunable}
     * @param delay long values to delay
     */
    void postDelay(final UIRunable r, final long delay);

    /**
     * See {@link android.content.res.Resources#getIdentifier(String, String, String)}
     *
     * @param context {@link Context}
     * @param type
     * @param name
     *
     * @return
     */
    int getIdentifier(Context context, String type, String name);

    /**
     * Get the resources id with special name.
     *
     * @param context
     * @param name
     *
     * @return
     */
    int getIdentifier(Context context, String name);

    /**
     * Get the drawable id with special name
     *
     * @param context
     * @param name
     *
     * @return
     */
    int getDrawableId(Context context, String name);

    String getString(Context context, int id);

    String getString(Context context, int id, Object... args);

    String[] getStringArray(Context context, int id);


    /**
     * Toast a message.
     *
     * @param context context
     * @param message message string
     * @param length  See {@link android.widget.Toast#LENGTH_LONG}, {@link android.widget.Toast#LENGTH_SHORT}.
     */
    void toast(Context context, String message, int length);

    /**
     * Toast a message.
     *
     * @param context context.
     * @param message message string.
     */
    void toast(Context context, String message);

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     * @param length  See {@link android.widget.Toast#LENGTH_LONG}, {@link android.widget.Toast#LENGTH_SHORT}.
     */
    void toast(Context context, int message, int length);

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     */
    void toast(Context context, int message);
}
