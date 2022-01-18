package com.kennie.utils.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：ToastHelper
 * Date：2021/12/12 23:15
 * Desc：Toast吐司帮助类
 */
public abstract class ToastHelper {

    @SuppressLint("StaticFieldLeak")
    private static Context ctx;
    private static Toast toast;

    private ToastHelper() {
        throw new UnsupportedOperationException("Can not create instance for class ToastUtils.");
    }


    /**
     * 初始化，在Application中
     *
     * @param ctx 上下文对象
     */
    public static void init(Context ctx) {
        ToastHelper.ctx = ctx;
    }


    /**
     * 短时间显示
     *
     * @param text 提示的内容
     */
    public static void shortT(CharSequence text) {
        toast(false, text);
    }

    /**
     * 长时间显示
     *
     * @param text 提示的内容
     */
    public static void longT(CharSequence text) {
        toast(true, text);
    }

    /**
     * 短时间显示
     *
     * @param resId 提示的内容资源id
     */
    public static void shortT(int resId) {
        toast(false, ctx.getResources().getString(resId));
    }

    /**
     * 长时间显示
     *
     * @param resId 提示的内容资源id
     */
    public static void longT(int resId) {
        toast(true, ctx.getResources().getString(resId));
    }

    /**
     * toast显示
     *
     * @param longToast 是否是长提显示
     * @param text      显示内容
     */
    @SuppressLint("ShowToast")
    private static void toast(boolean longToast, CharSequence text) {
        if (null == toast) {
            toast = Toast.makeText(ctx, text, longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
