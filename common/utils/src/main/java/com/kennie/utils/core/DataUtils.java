package com.kennie.utils.core;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：DataUtils
 * Date：2021/12/12 23:15
 * Desc：Data数据处理工具类
 *
 * <p>
 * --隐藏手机号中间四位                                    {@link #hidePhone(String phone)}
 * </p>
 */
public class DataUtils {

    /**
     * 隐藏手机号中间四位
     *
     * @param phone 手机号码
     * @return 结果
     */
    public static String hidePhone(String phone) {
        if (RegexUtils.isPhone(phone)) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return phone;
    }
}
