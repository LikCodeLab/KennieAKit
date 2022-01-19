package com.kennie.utils.core;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.kennie.utils.manager.UtilsManager;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：AppIntentUtils
 * Date：2021/12/12 23:15
 * Desc：AppIntent启动意图工具类
 *
 * <p>
 * --通过包名启动App                                      {@link #startApp(String packageName)}
 * --通过包名启动App                                      {@link #startApp(String packageName, String className)}
 * --通过包名启动App应用详情页                             {@link #startAppDetail(String packageName)}
 * --通过包名启动App卸载页                                 {@link #startAppUninstall(String packageName)}
 * --通过包名启动App通知页面                               {@link #startAppNotification(String packageName)}
 * --启动相关详情页(根据 android.provider.Settings)        {@link #startSettings(String actionType)}
 * --启动浏览器并打开指定URL页面                            {@link #startBrowser(String url)}
 * --启动浏览器并搜索指定内容                               {@link #startBrowserBySearch(String keyword)}
 * </p>
 */
public class AppIntentUtils {

    private static final String TAG = AppIntentUtils.class.getSimpleName();

    /**
     * 通过包名启动App
     *
     * @param packageName app包名
     * @return {@code true}: 启动成功<br> {@code false}: 启动失败
     */
    public static boolean startApp(@NonNull String packageName) {
        try {
            Intent intent = UtilsManager.get().getAppContext().getPackageManager().getLaunchIntentForPackage(packageName);
            startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "startApp 通过包名启动App 异常" + e.getMessage());
        }
        return false;
    }

    /**
     * 通过包名和类名启动App
     *
     * @param packageName app包名
     * @param className   需要启动的类名
     * @return {@code true}: 启动成功<br> {@code false}: 启动失败
     */
    public static boolean startApp(String packageName, String className) {
        try {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过包名启动App应用详情页
     *
     * @param packageName app包名
     */
    public static void startAppDetail(String packageName) {
        Intent intent = new Intent();
        int version = Build.VERSION.SDK_INT;
        if (version <= Build.VERSION_CODES.ECLAIR_MR1) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", packageName);
        }
        if (version == Build.VERSION_CODES.FROYO) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("pkg", packageName);
        } else if (version >= Build.VERSION_CODES.GINGERBREAD) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            //intent.setData(Uri.fromParts("package", packageName, null));
            intent.setData(Uri.parse("package:" + packageName));
        }
        startActivity(intent);
    }

    /**
     * 通过包名启动App卸载页
     *
     * @param packageName app包名
     */
    public static void startAppUninstall(String packageName) {
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + packageName));
        startActivity(intent);
    }

    /**
     * 通过包名启动App通知页面(可能无效 跳转到应用详情页面)
     *
     * @param packageName app包名
     */
    @SuppressLint({"ObsoleteSdkInt", "InlinedApi"})
    public static void startAppNotification(String packageName) {
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 适用于API26,即8.0（含8.0)以上可以使用
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, UtilsManager.get().getAppContext().getPackageName());
                startActivity(intent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 适用于API21-25,即5.0-7.1之间版本可以使用
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, UtilsManager.get().getAppContext().getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, UtilsManager.get().getAppContext().getApplicationInfo().uid);
                startActivity(intent);
            } else {
                // 其他情况跳转到App应用详情页
                startAppDetail(packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            startAppDetail(packageName);
        }
    }

    /**
     * 启动相关详情页(根据 android.provider.Settings)
     *
     * @param actionType – The type of action setting to return. Should be one of
     *                   Settings.ACTION_SETTINGS(系统设置), Settings.ACTION_WIFI_SETTINGS(无线网络),
     *                   Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS(应用管理),
     *                   or Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS(开发者选项).
     * @see android.provider.Settings
     */
    public static boolean startSettings(String actionType) {
        try {

            Intent intent = new Intent(actionType);
            startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 启动浏览器并打开指定URL页面
     *
     * @param url 打开的链接
     */
    public static boolean startBrowser(@NonNull String url) {
        // 检测是否是网址
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 启动浏览器并搜索指定内容
     *
     * @param keyword 搜索的内容
     */
    public static void startBrowserBySearch(@NonNull String keyword) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, keyword);
        startActivity(intent);
    }

    /**
     * 启动拨打电话
     *
     * @param phone 联系电话
     */
    public static void startCallPhone(String phone) {
        if (!TextUtils.isEmpty(phone) && RegexUtils.isPhone(phone)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(intent);
        }
    }

    /**
     * 启动发送短信
     *
     * @param phone 电话号码
     * @param sms   短信内容
     */
    public static void startSendSMS(String phone, String sms) {
        if (!TextUtils.isEmpty(phone) && RegexUtils.isPhone(phone)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
            intent.putExtra("sms_body", sms);
            startActivity(intent);
        }

    }

    /**
     * 启动发邮件
     *
     * @param email 邮箱
     */
    public static boolean startSendEmail(String email) {
        try {
            if (!TextUtils.isEmpty(email) && RegexUtils.isEmail(email)) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 跳转通知渠道设置页
     *
     * @param channelId 渠道ID
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void gotoChannelSetting(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, UtilsManager.get().getAppContext().getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        startActivity(intent);
    }

    private static void startActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UtilsManager.get().getApp().startActivity(intent);
    }
}
