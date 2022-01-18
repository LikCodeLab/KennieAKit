package com.kennie.utils.core;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

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
 * --获取App版本名称                                {@link #getAppVersionName()}
 * --获取App版本号                                  {@link #getAppVersionCode()}
 * --获取App图标                                {@link #getAppIcon()}
 * --获取App安装原始路径                         {@link #getAppInstallPath()}
 * --获取App原始安装文件(APK)                    {@link #getAppSourceFile()}
 * --判断APP是否安装                                {@link #isInstalled(String packageName)}
 * --判断APP是否为Debug模式                      {@link #isAppDebugMode()}
 * --判断App是否处于前台                         {@link #isAppTopForeground()}
 * --安装APP（兼容Android7.0及以上版本）          {@link #installApk(File file, String authority)}
 * </p>
 */
public abstract class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static Context ctx;

    /**
     * 初始化
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        ctx = context;
    }

    /**
     * 获取APP名称
     *
     * @return {@code the application's app name}: app应用名称 <br>
     */
    public static String getAppName() {
        PackageManager pm = ctx.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(ctx.getPackageName(), 0);
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
        return ctx.getPackageName();
    }

    /**
     * 获取APP版本名称
     *
     * @return {@code the application's app version name}: APP版本名称 <br>
     */
    public static String getAppVersionName() {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取APP版本号
     *
     * @return {@code the application's app version code}: APP版本号 <br>
     */
    public static long getAppVersionCode() {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            // Android 8.0 +
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                return packageInfo.getLongVersionCode();
            }
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取APP图标
     *
     * @return the application's app icon
     */
    public static Drawable getAppIcon() {
        PackageManager pm = ctx.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(ctx.getPackageName(), 0);
            return applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * APP安装原始路径
     *
     * @return the application's app path
     */
    public static String getAppInstallPath() {
        try {
            ApplicationInfo applicationInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 0);
            return applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * APP原始安装文件(APK)
     *
     * @return the application's app file.apk
     */
    public static File getAppSourceFile() {
        String sourceDir = getAppInstallPath();
        if (sourceDir != null) {
            return new File(sourceDir);
        }
        return null;
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
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断APP是否为Debug模式
     *
     * @return 是否成功 true|false
     */
    public static boolean isAppDebugMode() {
        try {
            String packageName = ctx.getPackageName();
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                ApplicationInfo info = packageInfo.applicationInfo;
                return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前App是否处于前台显示状态
     *
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isAppTopForeground() {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            /**
             * 在6.0/7.0等新版本中 可能还有另外几种状态:
             *        1.RunningAppProcessInfo.IMPORTANCE_TOP_SLEEPING(应用在前台时锁屏幕)，
             *          RunningAppProcessInfo.IMPORTANCE_FOREGROUND_SERVICE(应用开启了服务,然后锁屏幕,此时服务还是在前台运行)
             *       可以根据自己的实际情况决定上面列出的2个状态,是否算作前台状态;
             *
             */
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName.equals(ctx.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 安装APP（兼容Android7.0及以上版本）
     *
     * @param file      App文件路径
     * @param authority The authority of a FileProvider defined in a <provider> element in your app's manifest.
     */
//    public static void installApk(File file, String authority) {
//        if (file == null || !file.exists()) {
//
//            return;
//        }
//        try {
//            PackageManager pm = ctx.getPackageManager();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if (!pm.canRequestPackageInstalls()) {
//                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + ctx.getPackageName()));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ctx.startActivity(intent);
//                    return;
//                }
//            }
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri uri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                uri = FileProvider.getUriForFile(ctx, authority, file);
//            } else {
//                uri = Uri.fromFile(file);
//            }
//            intent.setDataAndType(uri, "application/vnd.android.package-archive");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            ctx.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
