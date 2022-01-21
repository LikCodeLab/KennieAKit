package com.kennie.base.project.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * @项目名 KennieProject
 * @类名称 IApplication
 * @类描述 统一管理Application 外部可继承
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/9 22:51
 */
public class IApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.start(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
