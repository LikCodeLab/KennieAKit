package com.want.base.sdk.framework.app.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.want.base.sdk.utils.CompatUtils;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 8/11/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ViewCompatEx {

    private ViewCompatEx() {
        // hide
    }


    @SuppressLint("NewApi")
    public static void setBackground(View view, Drawable drawable) {
        if (CompatUtils.has16()) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

}
