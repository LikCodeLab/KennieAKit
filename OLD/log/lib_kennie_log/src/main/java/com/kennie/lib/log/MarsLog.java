package com.kennie.lib.log;

import android.content.Context;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

/**
 * 初始化操作
 * <p>
 * true : 是否加载so库
 * Xlog.LEVEL_DEBUG 或  Xlog.LEVEL_INFO ：打印日志的等级
 * Xlog.AppednerModeSync: 同步
 * Xlog.AppednerModeAsync: 异步
 * cachePath: 这个参数必传，而且要data下的私有文件目录，例如 /data/data/packagename/files/xlog，
 * mmap文件会放在这个目录，如果传空串，可能会发生 SIGBUS 的crash。
 * logPath : xlog文件保存的路径
 * namePrefix: 一般填写项目名称
 * pub_key : 加/解密 用到的key （此处未加密）
 */
public class MarsLog {

    public static void init(Context context, String namePrefix, String logPath) {
        String cacheDir = context.getFilesDir() + "/xlog";
        if (BuildConfig.DEBUG) {
            Xlog.open(true, Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cacheDir, logPath, namePrefix, "");
            Log.setConsoleLogOpen(true);
        } else {
            Xlog.open(true, Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cacheDir, logPath, namePrefix, "");
            Log.setConsoleLogOpen(false);
        }
        Log.setLogImp(new Xlog());
    }

    // 此处也可以重新Log中打印的方法，使用MarsLog.v的形式进行日志打印，皆可
}
