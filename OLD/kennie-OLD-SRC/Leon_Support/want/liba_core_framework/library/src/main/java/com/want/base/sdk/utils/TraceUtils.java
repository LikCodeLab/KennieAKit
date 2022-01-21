package com.want.base.sdk.utils;

import android.os.SystemClock;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 调试工具
 * <br>
 */
public class TraceUtils {

    private static final String TAG = "[trace]";
    private static final Map<String, Long> TIME = new ConcurrentHashMap<String, Long>();

    private static final int INT_THOUSAND = 1000;

    /**
     * 给当前时间打上一个tag
     *
     * @param tag tag
     */
    public static void begin(String tag) {
        begin(tag, false);
    }

    /**
     * 给当前时间打上一个tag
     *
     * @param tag       tag
     * @param needPrint 是否打印begin的tag
     */
    public static void begin(String tag, boolean needPrint) {
        TIME.put(tag, SystemClock.uptimeMillis());
        if (needPrint) {
            Log.i(TAG, "begin: " + tag);
        }
    }

    /**
     * 计算当前时间和之前打上tag的时间差
     *
     * @param tag tag
     *
     * @return 运行的时间(ms)
     */
    public static long end(String tag) {
        return end(tag, "");
    }

    /**
     * 计算当前时间和之前打上tag的时间差
     *
     * @param tag       tag
     * @param needPrint 是否打印begin的tag
     *
     * @return 运行的时间(ms)
     */
    public static long end(String tag, boolean needPrint) {
        long t = lap(tag, "", needPrint);
        TIME.remove(tag);
        return t;
    }

    /**
     * 计算当前时间和之前打上tag的时间，并删除tag
     *
     * @param tag   tag
     * @param extra 额外信息
     *
     * @return 运行的时间(ms)
     */
    public static long end(String tag, String extra) {
        long t = lap(tag, extra, true);
        TIME.remove(tag);
        return t;
    }


    /**
     * 计算当前时间和之前打上tag的时间差，不删除tag
     *
     * @param tag tag
     */
    public static void lap(String tag) {
        lap(tag, "", true);
    }

    /**
     * 计算当前时间和之前打上tag的时间差，不删除tag
     *
     * @param tag   tag
     * @param extra extra
     */
    public static void lap(String tag, String extra) {
        lap(tag, extra, true);
    }

    /**
     * 计算当前时间和之前打上tag的时间差，不删除tag
     *
     * @param tag       tag
     * @param extra     额外信息
     * @param needPrint 是否打印begin的tag
     *
     * @return 运行的时间(ms)
     */
    public static long lap(String tag, String extra, boolean needPrint) {
        extra = extra != null && extra.length() > 0 ? ", " + extra : "";
        long t = -1;
        if (TIME.containsKey(tag)) {
            t = (SystemClock.uptimeMillis() - TIME.get(tag));
            String time = t > INT_THOUSAND ? (double) t / INT_THOUSAND + "s" : t + "ms";
            if (needPrint) {
                Log.i(TAG, tag + ": " + time + ", " + extra);
            }
        } else {
            if (needPrint) {
                Log.e(TAG, "You did NOT CALL StopWatch.begin(" + tag + ")");
            }
        }
        return t;
    }

    /**
     * 打一个log
     *
     * @param msg Message
     */
    public static void log(String msg) {
        log(TAG, msg);
    }

    /**
     * 打log
     *
     * @param tag tag
     * @param msg Message
     */
    public static void log(Object tag, String msg) {
        log(tag == null ? TAG : tag.getClass().getSimpleName(), msg);
    }

    /**
     * 打一个log
     *
     * @param tag Tag
     * @param msg Message
     */
    public static void log(String tag, String msg) {
        Log.i(tag, msg);
    }
}
