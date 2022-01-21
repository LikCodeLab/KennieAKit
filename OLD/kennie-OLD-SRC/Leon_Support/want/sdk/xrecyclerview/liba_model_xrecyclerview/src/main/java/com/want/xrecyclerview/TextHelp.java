package com.want.xrecyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;


/**
 * <b>Project:</b> android-HollyWant<br>
 * <b>Create Date:</b> 2016/05/25<br>
 * <b>Author:</b> LiDuo<br>
 * <b>Description:</b> <br>
 */

public class TextHelp {

    private static SharedPreferences sp;
    private static final String SP_NAME = "refresh_tag";


    /**
     * 时间戳的存储
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveTimestamp(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferencesCompat.EditorCompat.getInstance().apply(sp.edit().putString(key, value));
    }

    /**
     * 时间戳的获取
     *
     * @param context
     * @param key
     */
    public static String getTimestamp(Context context, String key) {
        return !TextUtils.isEmpty(key) ? context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, "") : "";
    }


}
