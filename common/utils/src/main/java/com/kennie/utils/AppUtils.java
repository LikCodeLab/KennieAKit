package com.kennie.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.kennie.utils.manager.UtilsManager;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：AppUtils
 * Date：2021/12/12 23:15
 * Desc：App管理工具类
 *
 * <p>
 * --获取APP名称                                    {@link #getAppName()}
 * --获取APP包名                                    {@link #getAppPackage()}
 * --判断APP是否安装                                {@link #isInstalled(String packageName)}
 * </p>
 */
public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();


    /**
     * 获取APP名称
     *
     * @return {@code the application's app name}: app应用名称 <br>
     */
    public static String getAppName() {
        PackageManager pm = UtilsManager.get().getAppContext().getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(UtilsManager.get().getAppContext().getPackageName(), 0);
            return String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取APP包名
     *
     * @return {@code the application's package name}: app应用包名 <br>
     */
    public static String getAppPackage() {
        return UtilsManager.get().getAppContext().getPackageName();
    }


    /**
     * 判断APP是否安装
     *
     * @param packageName app包名
     * @return {@code true}: 已安装<br> {@code false}: 未安装
     */
    public static boolean isInstalled(@NonNull String packageName) {
        if (TextUtils.isEmpty(packageName)) return false;
        try {
            PackageInfo info = UtilsManager.get().getAppContext().getPackageManager().getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
