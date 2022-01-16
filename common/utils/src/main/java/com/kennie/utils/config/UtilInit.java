package com.kennie.utils.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * 工具模块 初始化基类
 */
public final class UtilInit {


    //全局上下文
    private static Application sApp;

    private UtilInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param app
     */
    public static void init(final Application app) {
        if (app == null) {
            Log.e("UtilInit", "Application is null");
            return;
        }
        if (sApp == null) {
            sApp = app;
            return;
        }
        if (sApp.equals(app)) return;
        sApp = app;
    }


    public static Application getApp() {
        if (sApp != null) return sApp;
        throw new NullPointerException("should be initialized UtilInit in application.");
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getAppContext() {
        if (sApp != null) {
            return sApp.getApplicationContext();
        }
        throw new NullPointerException("should be initialized UtilInit in application.");
    }

}
