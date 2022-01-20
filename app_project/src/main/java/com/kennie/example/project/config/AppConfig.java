package com.kennie.example.project.config;

import com.kennie.example.project.BuildConfig;

/**
 * App 配置管理类
 */
public class AppConfig {

    /**
     * 当前是否为调试模式
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 获取当前构建的模式
     */
    public static String getBuildType() {
        return BuildConfig.BUILD_TYPE;
    }

    /**
     * 当前是否开启日志打印功能
     */
    public static boolean isLogEnable() {
        return BuildConfig.LOG_ON;
    }

}
