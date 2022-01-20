package com.kennie.example.utils;

import android.app.Application;

import com.kennie.utils.UtilsManager;

/**
 * project : KennieAKit
 * class_name :  App
 * author : Kennie
 * date : 2022/1/15 20:26
 * desc :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UtilsManager.get()
                .init(this , BuildConfig.DEBUG);
        //SPUtils.get().init(this);

    }
}
