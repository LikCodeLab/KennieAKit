package com.kennie.utils.core;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


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
public abstract class SPUtils {

    private static final String DEFAULT_FILE_NAME = "sp_utils_config";
    private static SharedPreferences sp;

    /**
     * 初始化
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        init(context, DEFAULT_FILE_NAME);
    }

    /**
     * 初始化
     *
     * @param context   上下文对象
     * @param file_name data/data/<packagename>/shared_prefs下的文件名
     */
    public static void init(Context context, String file_name) {
        sp = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
    }

    /**
     * 获取sp编辑器
     *
     * @return sp编辑器
     */
    private static SharedPreferences.Editor edit() {
        return sp.edit();
    }

    // ================================== putData ==================================

    /**
     * 存储不同类型的值(使用apply方法，该方法没有返回值，提交为异步操作)
     *
     * @param key   key名称
     * @param value 不同类型存储值
     */
    public static void putData(String key, Object value) {
        if (value instanceof String) {
            // string类型
            edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            edit().putLong(key, (Long) value).apply();
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
            return edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            return edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            return edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            return edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            return edit().putLong(key, (Long) value).commit();
        }
        return false;
    }


    // ================================== getData ==================================

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public static long getLong(String key) {
        return sp.getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    // ================================== contains ==================================

    public static boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    // ================================== remove ==================================

    /**
     * 移除一条记录
     *
     * @param key 名称
     */
    public static void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    public static void clear() {
        sp.edit().clear().apply();
    }

}
