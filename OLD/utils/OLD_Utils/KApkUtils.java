package com.kennie.android.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * APK 管理类
 */
public class KApkUtils {

    private static final String TAG = "KApkUtils";

    private KApkUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 根据安装包路径获取 包名
     *
     * @param context
     * @param filePath 安装包路径
     * @return
     */
    public static String getPackageName(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            return appInfo.packageName;  //得到安装包名称
        }
        return "";
    }

    /**
     * 根据安装包路径获取 版本号
     *
     * @param context
     * @param filePath
     */
    public static int getPathVersionCode(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.versionCode;
        }
        return 0;
    }

    /**
     * 根据安装包路径获取 版本名称
     *
     * @param filePath
     */
    public static String getPathVersionName(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);

        if (null != info) {
            return info.versionName;
        } else {
            return "0.0.0";
        }
    }

    /**
     * 根据安装包路径获取 应用图标
     *
     * @param filePath
     */
    public static Drawable getPathLogo(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        ApplicationInfo applicationInfo = info.applicationInfo;

        if (applicationInfo != null) {
            return packageManager.getApplicationIcon(applicationInfo);
        } else {
            return null;
        }
    }
}
