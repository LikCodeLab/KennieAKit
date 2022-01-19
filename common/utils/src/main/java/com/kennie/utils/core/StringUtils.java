package com.kennie.utils.core;

import android.text.TextUtils;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：StringUtils
 * Date：2021/12/12 23:15
 * Desc：字符串处理类
 *
 * <p>
 * --*********                                      {@link #}
 * </p>
 */
public class StringUtils {


    /**
     * 字符串判空 包含 null 和 ""
     *
     * @param str 字符串
     * @return true false
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

}
