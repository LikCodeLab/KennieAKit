package com.kennie.base.project;

import android.app.Application;
import android.content.Context;

/**
 * @项目名 KennieProject
 * @类名称 BaseTools
 * @类描述 工具初始化演示
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/9 23:30
 */
public class BaseTools {

    private static final String TAG = "BaseTools";
    //全局上下文
    static Context sApp;
    private static volatile BaseTools sInstance;

    /**
     * 单例模式
     *
     * @return
     */
    public static BaseTools instance() {
        if (sInstance == null) {
            synchronized (BaseTools.class) {
                if (sInstance == null) {
                    sInstance = new BaseTools();
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
        BaseTools.sApp = application.getApplicationContext();
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
        throw new NullPointerException("BaseTools should be initialized in application");
    }

}
