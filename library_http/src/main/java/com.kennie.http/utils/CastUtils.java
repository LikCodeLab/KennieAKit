package com.kennie.http.utils;

/**
 * <p>
 * Description :类型转换工具类，统一加 unchecked 注解消除 warring
 */
public class CastUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

}
