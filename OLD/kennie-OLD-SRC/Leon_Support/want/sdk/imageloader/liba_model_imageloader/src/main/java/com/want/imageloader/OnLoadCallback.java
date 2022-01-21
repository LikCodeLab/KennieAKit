package com.want.imageloader;

import android.graphics.drawable.Drawable;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Image load callback. The {@link OnLoadCallback#onLoadCallback(Drawable)} method will be called
 * after the image load completed,
 * <p/>
 * <br>
 */
public interface OnLoadCallback {

    /**
     * Called when the image loaded.
     *
     * @param drawable {@link Drawable}
     */
    void onLoadCallback(Drawable drawable);
}
