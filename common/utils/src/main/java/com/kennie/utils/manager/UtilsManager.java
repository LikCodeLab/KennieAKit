package com.kennie.utils.manager;

import android.app.Application;
import android.content.Context;

import com.kennie.utils.MMKVUtils;
import com.kennie.utils.SPUtils;
import com.kennie.utils.ScreenUtils;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：UtilManager
 * Date：2021/12/12 23:15
 * Desc：Utils工具类统一管理器
 */
public class UtilsManager {

    //全局上下文
    private volatile static UtilsManager instance;
    private Application mApp;

    private UtilsManager() {
        if (null != instance) {
            throw new UnsupportedOperationException("u can't instantiate singleton class.");
        }
    }

    public static UtilsManager get() {
        if (instance == null) {
            synchronized (UtilsManager.class) {
                if (instance == null) {
                    instance = new UtilsManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param app 上下文对象
     * @return 当前对象
     */
    public UtilsManager init(Application app) {
        return init(app, false);
    }


    /**
     * 初始化
     *
     * @param app          上下文对象
     * @param debugEnabled 是否是debug模式
     * @return 当前实例对象
     */
    public UtilsManager init(Application app, boolean debugEnabled) {
        mApp = app;
        MMKVUtils.get().init(mApp);
        SPUtils.init(mApp);
        ScreenUtils.init(mApp);
        return this;
    }

    public Application getApp() {
        if (mApp != null) return mApp;
        throw new NullPointerException("should be initialized UtilManager in application.");
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public Context getAppContext() {
        if (mApp != null) {
            return mApp.getApplicationContext();
        }
        throw new NullPointerException("should be initialized UtilManager in application.");
    }

}
