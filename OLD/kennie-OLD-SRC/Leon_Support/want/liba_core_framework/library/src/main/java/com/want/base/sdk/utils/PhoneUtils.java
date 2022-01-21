package com.want.base.sdk.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Utils for access hardware info or service.
 * <br>
 */
@SuppressWarnings("unused")
public class PhoneUtils {

    private static final String TAG = "PhoneUtils";

    private PhoneUtils() {

    }

    /**
     * Get the screen's width.
     *
     * @param context context
     *
     * @return the width in pix.
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Get the screen's height.
     *
     * @param context context
     *
     * @return the height in pix
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Get the screen's width and screen's height.
     *
     * @param context context
     *
     * @return int[0], width; int[1], height.
     */
    public static int[] getScreenSize(Context context) {
        return new int[]{getScreenWidth(context), getScreenHeight(context)};
    }

    /**
     * Get the cpu info.
     *
     * @return string[0], cpu modle; string[1], cpu frequency.
     */
    public static String[] getCpuInfo() {
        String path = "/proc/cpuinfo";
        String data;
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] array;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader localBufferedReader = new BufferedReader(fileReader, 8192);
            data = localBufferedReader.readLine();
            array = data.split("\\s+");
            for (int i = 2; i < array.length; i++) {
                cpuInfo[0] = cpuInfo[0] + array[i] + " ";
            }
            data = localBufferedReader.readLine();
            array = data.split("\\s+");
            cpuInfo[1] += array[2];
            localBufferedReader.close();
        } catch (Exception e) {
            // ignore
        }
        Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }

    /**
     * Get the system memory info
     *
     * @param context context
     *
     * @return string[0], avail memory; string[1], total memory.
     */
    public static String[] getMemoryInfo(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        String path = "/proc/meminfo";// 系统内存信息文件
        String data;
        String[] strings;
        long totalMem = 0;

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
            data = bufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            strings = data.split("\\s+");

            totalMem = Long.valueOf(strings[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            bufferedReader.close();

        } catch (Exception e) {
            // ignore
        }
        return new String[]{String.valueOf(Formatter.formatFileSize(context, memoryInfo.availMem)),
                String.valueOf(Formatter.formatFileSize(context, totalMem))};
    }


    /**
     * Get the model's device id or imei.
     *
     * @param context context
     *
     * @return the model's device id.
     */
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * Get the model's imsi or subscriberid
     *
     * @param context context
     *
     * @return the model's imsi
     */
    public static String getImsi(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId();
    }

    /**
     * Get hte model's name
     *
     * @param context context
     *
     * @return model name
     */
    public static String getModel(Context context) {
        return Build.MODEL;
    }

    /**
     * Get the model's brand
     *
     * @param context context
     *
     * @return model brand
     */
    public static String getBrand(Context context) {
        return Build.BRAND;
    }

    /**
     * Get the model's telephone number
     *
     * @param context context
     *
     * @return telephone number
     */
    public static String getTelephoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }
}
