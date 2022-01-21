package com.kennie.base.project;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kennie.project.BuildConfig;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter(); // 初始化路由
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
