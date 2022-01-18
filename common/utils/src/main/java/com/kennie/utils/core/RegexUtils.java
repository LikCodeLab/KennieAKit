package com.kennie.utils.core;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：RegexUtils
 * Date：2021/12/12 23:15
 * Desc：正则处理类
 *
 * <p>
 * --验证是否是数字                                    {@link #isNumber(String text)}
 * --验证是否是手机号                                    {@link #isPhone(String strTelNo)}
 * --验证是否是邮箱                                     {@link #isEmail(String strEmail)}
 * --验证是否是银行卡号                                  {@link #isBankCardNo(String strBankNo)}
 * --验证身份证号                             {@link #isIDCardNo(String strIDCardNo)}
 * --验证是否是url                             {@link #isUrl(String text)}
 * </p>
 */
public class RegexUtils {

    // 数字
    private final static String REGEX_NUMBER = "^\\d+$";
    // 手机号码
    private final static String REGEX_PHONE = "^((13[0-9])|(14[0-9])|(15[0-9])|(166)|(17[0-9])|(18[0-9])|(198)|(199))\\d{8}$";
    // 邮箱
    // ^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.([a-zA-Z0-9_-])+)+$
    // ^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$
    private final static String REGEX_EMAIL = "^(\\w+)(\\.\\w+)*@(\\w{2,8}\\.){1,3}\\w{2,8}$";
    // 银行卡号
    private final static String REGEX_BANKCARD_NO = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
    // 网址
    private final static String REGEX_URL = "^https?://(\\w+)+(\\.\\w+)+(/\\w+)*/?$";

    /**
     * 验证是否是数字
     *
     * @param text 待检查文本
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isNumber(String text) {
        return match(text, REGEX_NUMBER);
    }

    /**
     * 验证是否是手机号
     *
     * @param strTelNo 待检查手机号 新增移动198 166联通 199电信 14*号段
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isPhone(String strTelNo) {
        Pattern p = Pattern.compile(REGEX_PHONE, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strTelNo);
        return m.matches();
    }

    /**
     * 验证是否是邮箱
     *
     * @param strEmail 待检查邮箱
     * @return true {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isEmail(String strEmail) {
        return match(strEmail, REGEX_EMAIL);
    }


    /**
     * 验证是否是银行卡号
     *
     * @param strBankNo 待检查银行卡号
     * @return true {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isBankCardNo(String strBankNo) {
        return match(strBankNo, REGEX_BANKCARD_NO);
    }


    /**
     * 验证身份证号是否符合规则
     *
     * @param strIDCardNo 待检查身份证号
     * @return true {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isIDCardNo(String strIDCardNo) {
        String regex0 = "[0-9]{17}x";
        String regex1 = "[0-9]{15}";
        String regex2 = "[0-9]{18}";
        return strIDCardNo.matches(regex0) || strIDCardNo.matches(regex1) || strIDCardNo.matches(regex2);
    }

    /**
     * 验证是否是url
     *
     * @param text 待检查文本
     * @return true {@code true}: 是 <br> {@code false}: 否
     */
    public static boolean isUrl(String text) {
        return match(text, REGEX_URL);
    }

    /**
     * 验证字符串是否符合规则
     *
     * @param text  待检查字符串
     * @param regex 匹配正则表达式
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    private static boolean match(String text, String regex) {
        return !TextUtils.isEmpty(text) && Pattern.compile(regex).matcher(text).matches();
    }
}
