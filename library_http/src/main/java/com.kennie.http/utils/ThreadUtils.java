package com.kennie.http.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Description :获取全局的 mainHandler
 */
public class ThreadUtils {
    private static Handler mainHandler;

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }
}
