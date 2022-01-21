
package com.leon.utils.temp.support;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.leon.utils.R;


/**
 * 对话框管理
 */
public class DialogManagerUtil {

    /**
     * Gets the no network dialog.
     *
     * @param ctx the ctx
     * @return the no network dialog
     */
    public static Dialog getNoNetworkDialog(Context ctx) {
        Dialog dialog = new Dialog(ctx, R.style.noBackgroundDialog);
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View contentView = layoutInflater.inflate(R.layout.dialog_no_network,
                null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(contentView);
        return dialog;
    }


    /**
     * Show dialog.
     *
     * @param ctx    the ctx
     * @param dialog the dialog
     */
    public static void showDialog(Context ctx, Dialog dialog) {
        if (dialog != null && !dialog.isShowing() && ctx != null
                && !((Activity) ctx).isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏dialog，加了context生命判断，避免窗口句柄泄漏.
     *
     * @param ctx    dialog依赖的activity
     * @param dialog 欲隐藏的dialog
     */
    public static void dismissDialog(Activity ctx, Dialog dialog) {
        if (dialog != null && dialog.isShowing() && ctx != null
                && !ctx.isFinishing())
            dialog.dismiss();
    }


}
