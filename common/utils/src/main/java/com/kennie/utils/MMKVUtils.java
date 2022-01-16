package com.kennie.utils;

import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：MMKVUtils
 * Date：2021/12/12 23:15
 * Desc：MMKV封装工具类
 *
 * <p>
 * </p>
 */
public class MMKVUtils {

    private volatile static MMKVUtils instance;


    public static MMKVUtils get() {
        if (instance == null) {
            synchronized (MMKVUtils.class) {
                if (instance == null) {
                    instance = new MMKVUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        MMKV.initialize(context);
    }

}
