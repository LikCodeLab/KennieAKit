package com.leon.support.base.framework.core;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.widget.Toast;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class BaseUIHelperImpl implements ISDKUIHelper {

    private final int[] mHandlerLock = new int[0];
    private Handler mHandler;

    public BaseUIHelperImpl() {

    }

    /**
     * Get the drawable id with special name
     *
     * @param context
     * @param name
     *
     * @return
     */
    @Override
    public int getDrawableId(Context context, String name) {
        return getIdentifier(context, "drawable", name);
    }

    /**
     * Get a single instance handler
     *
     * @return {@link Handler}
     */
    @Override
    public Handler getHandler() {
        if (null == mHandler) {
            synchronized (mHandlerLock) {
                if (null == mHandler) {
                    mHandler = new Handler();
                }
            }
        }
        return mHandler;
    }

    /**
     * Run a {@link Runnable} on the main thread.
     *
     * @param r {@link Runnable}
     */
    @Override
    public void post(UIRunable r) {
        getHandler().post(r);
    }

    /**
     * Run a {@link Runnable} on the main thread.
     *
     * @param r     {@link Runnable}
     * @param delay long values to delay
     */
    @Override
    public void postDelay(UIRunable r, long delay) {
        getHandler().postDelayed(r, delay);
    }

    /**
     * See {@link Resources#getIdentifier(String, String, String)}
     *
     * @param context {@link Context}
     * @param type
     * @param name
     *
     * @return
     */
    @Override
    public int getIdentifier(Context context, String type, String name) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    /**
     * Get the resources id with special name.
     *
     * @param context
     * @param name
     *
     * @return
     */
    @Override
    public int getIdentifier(Context context, String name) {
        return getIdentifier(context, "id", name);
    }

    @Override
    public String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    @Override
    public String getString(Context context, int id, Object... args) {
        return context.getResources().getString(id, args);
    }

    @Override
    public String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message string
     * @param length  See {@link Toast#LENGTH_LONG}, {@link Toast#LENGTH_SHORT}.
     */
    @Override
    public void toast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }

    /**
     * Toast a message.
     *
     * @param context context.
     * @param message message string.
     */
    @Override
    public void toast(Context context, String message) {
        toast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     * @param length  See {@link Toast#LENGTH_LONG}, {@link Toast#LENGTH_SHORT}.
     */
    @Override
    public void toast(Context context, int message, int length) {
        Toast.makeText(context, message, length).show();
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     */
    @Override
    public void toast(Context context, int message) {
        toast(context, message, Toast.LENGTH_SHORT);
    }
}
