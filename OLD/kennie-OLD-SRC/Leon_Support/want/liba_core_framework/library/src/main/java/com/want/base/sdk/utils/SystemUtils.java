package com.want.base.sdk.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/7/6<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SystemUtils {

    private SystemUtils() {
        // no-instance
    }

    /**
     * 切换输入法显示状态, 如果输入法已显示, 则隐藏输入法, 反之则显示输入法.
     *
     * @param context
     */
    public static void toggleInputMethod(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showInputMethod(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!imm.isActive()) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        }
    }

    public static void showInputMethod(View v) {
        final Context context = v.getContext();
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(v.getWindowToken(), 0);
    }

    public static void hideInputMethod(View v) {
        final Context context = v.getContext();
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void hideInputMethod(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isInputMethodActive(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }
}
