package com.kennie.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：StringUtils
 * Date：2021/12/12 23:15
 * Desc：正则处理类
 *
 * <p>
 * --验证是否是手机号                                    {@link #isMobile(String strTelNo)}
 * --验证是否是邮箱                                     {@link #isEmail(String strEmail)}
 * --验证是否是银行卡号                                  {@link #isBankCardNo(String strBankNo)}
 * --验证身份证号是否符合规则                             {@link #isIDCardNo(String strIDCardNo)}

 * </p>
 */
public class RegexUtils {


    /**
     * 验证是否是手机号
     *
     * @param strTelNo 手机号 新增移动198 166联通 199电信 14*号段
     * @return true 是 false 否
     */
    public static boolean isPhone(String strTelNo) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(166)|(17[0-9])|(18[0-9])|(198)|(199))\\d{8}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strTelNo);
        return m.matches();
    }


    /**
     * 验证是否是邮箱
     *
     * @param strEmail 邮箱
     * @return true 是 false 否
     */
    public static boolean isEmail(String strEmail) {
        //String regex = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }


    /**
     * 验证是否是银行卡号
     *
     * @param strBankNo 银行卡号
     * @return true 是 false 否
     */
    public static boolean isBankCardNo(String strBankNo) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(strBankNo);
        return m.matches();
    }


    /**
     * 验证身份证号是否符合规则
     *
     * @param strIDCardNo 身份证号
     * @return true 是 false 否
     */
    public static boolean isIDCardNo(String strIDCardNo) {
        String regex0 = "[0-9]{17}x";
        String regex1 = "[0-9]{15}";
        String regex2 = "[0-9]{18}";
        return strIDCardNo.matches(regex0) || strIDCardNo.matches(regex1) || strIDCardNo.matches(regex2);
    }
}
