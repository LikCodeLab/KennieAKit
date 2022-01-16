package com.kennie.example.utils;

import android.app.Application;

import com.kennie.utils.MMKVUtils;
import com.kennie.utils.SPUtils;
import com.kennie.utils.config.UtilInit;

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
        UtilInit.init(this);
        MMKVUtils.get().init(this);
        //SPUtils.get().init(this);

    }
}
