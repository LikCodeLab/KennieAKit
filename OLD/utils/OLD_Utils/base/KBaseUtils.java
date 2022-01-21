package com.kennie.kit.utils;

import android.app.Application;
import android.content.Context;

/**
 * 工具 单例化
 */
public final class KBaseUtils {

    private static final String TAG = "KBaseUtils";
    //全局上下文
    static Context sApp;
    private static volatile KBaseUtils sInstance;

    /**
     * 单例模式
     *
     * @return KBaseUtils
     */
    public static KBaseUtils instance() {
        if (sInstance == null) {
            synchronized (KBaseUtils.class) {
                if (sInstance == null) {
                    sInstance = new KBaseUtils();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化上下文，注册interface
     *
     * @param application 全局上下文
     */
    public void create(Application application) {
        KBaseUtils.sApp = application.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (sApp != null) {
            return sApp;
        }
        throw new NullPointerException("should be initialized in application");
    }

}
