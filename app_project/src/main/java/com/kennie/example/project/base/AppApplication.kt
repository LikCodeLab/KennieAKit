package com.kennie.example.project.base

import android.app.ActivityManager
import com.kennie.base.project.App

class AppApplication : App() {

    override fun onCreate() {
        super.onCreate()
        initSDK(this);
    }

    private fun initSDK(application: AppApplication) {

        // Activity 栈管理初始化
        //ActivityManager.getInstance().init(application);

        // MMKV 初始化
        //MMKV.initialize(application);
    }

}