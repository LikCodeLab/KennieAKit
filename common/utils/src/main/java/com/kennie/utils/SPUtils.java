package com.kennie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.kennie.utils.config.UtilInit;
import com.tencent.mmkv.MMKV;


/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：SPUtils
 * Date：2021/12/12 23:15
 * Desc：SharedPreferences封装工具类
 *
 * <p>
 * --存储不同类型的值                                {@link #putData(String key, Object value)}
 * --存储不同类型的值(返回值)                         {@link #putDataForResult(String key, Object value)}
 * --获取String类型的值                              {@link #getString(String key)}
 * --获取String类型的值                              {@link #getString(String key, String defaultValue)}
 * --获取Boolean类型的值                             {@link #getBoolean(String key)}
 * --获取Boolean类型的值                             {@link #getBoolean(String key, boolean defaultValue)}
 * --获取Int类型的值                                 {@link #getInt(String key)}
 * --获取Int类型的值                                 {@link #getInt(String key, int defaultValue)}
 * --获取Float类型的值                               {@link #getFloat(String key)}
 * --获取Float类型的值                               {@link #getFloat(String key, float defaultValue)}
 * --获取Long类型的值                                {@link #getLong(String key)}
 * --获取Long类型的值                                {@link #getLong(String key, long defaultValue)}
 * --判断key是否存在                                 {@link #contains(String key)}
 * --移除单个key                                     {@link #remove(String key)}
 * --清空全部key-value                               {@link #clear()}
 * </p>
 */
public class SPUtils {

    private static final String DEFAULT_FILE_NAME = "sp_utils_config";

    /**
     * 获取SharedPreferences实例对象（默认）
     *
     * @return
     */
    private static SharedPreferences getPreferences() {
        return getPreferences(DEFAULT_FILE_NAME);
    }

    /**
     * 获取SharedPreferences实例对象（默认）
     *
     * @param file_name data/data/<packagename>/shared_prefs下的文件名
     * @return 返回实例对象
     */
    private static SharedPreferences getPreferences(String file_name) {
        if (TextUtils.isEmpty(file_name)) {
            return PreferenceManager.getDefaultSharedPreferences(UtilInit.getAppContext());
        } else {
            return UtilInit.getAppContext().getSharedPreferences(file_name, Context.MODE_PRIVATE);
        }
    }

    // ================================== putData ==================================

    /**
     * 存储不同类型的值(使用apply方法，该方法没有返回值，提交为异步操作)
     *
     * @param key   key名称
     * @param value 不同类型存储值
     */
    public void putData(String key, Object value) {
        if (value instanceof String) {
            // string类型
            getPreferences().edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            getPreferences().edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            getPreferences().edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            getPreferences().edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            getPreferences().edit().putLong(key, (Long) value).apply();
        }
    }


    /**
     * 存储不同类型的值(使用apply方法，该方法没有返回值，提交为异步操作)
     *
     * @param key   key名称
     * @param value 不同类型存储值
     */
    public boolean putDataForResult(String key, Object value) {
        if (value instanceof String) {
            // string类型
            return getPreferences().edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            return getPreferences().edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            return getPreferences().edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            return getPreferences().edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            return getPreferences().edit().putLong(key, (Long) value).commit();
        }
        return false;
    }


    // ================================== getData ==================================

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    public static long getLong(String key) {
        return getPreferences().getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    // ================================== contains ==================================

    public static boolean contains(@NonNull String key) {
        return getPreferences().contains(key);
    }

    // ================================== remove ==================================

    public static void remove(@NonNull String key) {
        getPreferences().edit().remove(key).apply();
    }

    public static void clear() {
        getPreferences().edit().clear().apply();
    }

}
