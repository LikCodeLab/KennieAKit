package com.kennie.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.kennie.utils.config.UtilInit;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：AppUtils
 * Date：2021/12/12 23:15
 * Desc：App管理工具类
 *
 * <p>
 * --判断APP是否安装                                {@link #isInstalled(String packageName)}
 * </p>
 */
public class AppUtils {


    /****
     * 判断APP是否安装
     * @param packageName 包名
     * @return
     */
    public static boolean isInstalled(@NonNull String packageName) {
        if (TextUtils.isEmpty(packageName)) return false;
        try {
            PackageInfo info = UtilInit.getAppContext().getPackageManager().getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
