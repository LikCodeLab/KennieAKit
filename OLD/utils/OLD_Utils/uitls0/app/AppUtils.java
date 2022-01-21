package cn.lukaslee.utils.support.app;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LukasLee
 * @time 2020/3/30 15:04
 * @classname DateUtils
 * description:APP管理应用工具类
 */
@SuppressWarnings("unused")
public class AppUtils {


    /**
     * 获取应用名称
     *
     * @param ctx 上下文
     * @return 应用名称
     */
    public static String getAppName(Context ctx) {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(ctx.getPackageName(), 0);
            return (String) packageManager.getApplicationLabel(appInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取应用版本号
     *
     * @param ctx 上下文
     * @return 版本号
     */
    public static String getAppVersion(Context ctx) {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取 开机时间
     */
    public static String getBootTimeString() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((ut / 3600));
        int m = (int) ((ut / 60) % 60);
        return h + ":" + m;
    }


    /**
     * 获取android手机品牌、商家、版本号等信息
     *
     * @return 格式化的手机基础信息
     */
    public static String printSystemInfo() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  系统信息  ").append(time).append(" ______________");
        sb.append("\nID                 :").append(Build.ID);
        sb.append("\nBRAND              :").append(Build.BRAND);
        sb.append("\nMODEL              :").append(Build.MODEL);
        sb.append("\nRELEASE            :").append(Build.VERSION.RELEASE);
        sb.append("\nSDK                :").append(Build.VERSION.SDK);

        sb.append("\n_______ OTHER _______");
        sb.append("\nBOARD              :").append(Build.BOARD);
        sb.append("\nPRODUCT            :").append(Build.PRODUCT);
        sb.append("\nDEVICE             :").append(Build.DEVICE);
        sb.append("\nFINGERPRINT        :").append(Build.FINGERPRINT);
        sb.append("\nHOST               :").append(Build.HOST);
        sb.append("\nTAGS               :").append(Build.TAGS);
        sb.append("\nTYPE               :").append(Build.TYPE);
        sb.append("\nTIME               :").append(Build.TIME);
        sb.append("\nINCREMENTAL        :").append(Build.VERSION.INCREMENTAL);

        sb.append("\n_______ CUPCAKE-3 _______");
        sb.append("\nDISPLAY            :").append(Build.DISPLAY);

        sb.append("\n_______ DONUT-4 _______");
        sb.append("\nSDK_INT            :").append(Build.VERSION.SDK_INT);
        sb.append("\nMANUFACTURER       :").append(Build.MANUFACTURER);
        sb.append("\nBOOTLOADER         :").append(Build.BOOTLOADER);
        sb.append("\nHARDWARE           :").append(Build.HARDWARE);
        sb.append("\nUNKNOWN            :").append(Build.UNKNOWN);
        sb.append("\nCODENAME           :").append(Build.VERSION.CODENAME);

        sb.append("\n_______ GINGERBREAD-9 _______");
        sb.append("\nSERIAL             :").append(Build.SERIAL);
        return sb.toString();
    }


    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {

        return Runtime.getRuntime().maxMemory() / 1024;
    }

}
