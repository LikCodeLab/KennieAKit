package com.kennie.log.example;

import android.app.Application;
import android.os.Environment;

import com.kennie.lib.log.MarsLog;
import com.tencent.mars.xlog.Log;

public class LogApplication extends Application {

    //保存路径
    final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    final String logPath = SDCARD + "/app_log_mars_example/log";

    @Override
    public void onCreate() {
        super.onCreate();
        initMarsLog();
    }

    private void initMarsLog() {
        MarsLog.init(this, "app_log_mars_example", logPath);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.appenderFlush();
        Log.appenderClose();
    }
}
