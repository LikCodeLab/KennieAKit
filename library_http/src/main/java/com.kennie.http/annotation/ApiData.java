package com.kennie.http.annotation;

import com.kennie.http.entity.IApiData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Description :注解指定接口的包裹 ApiData 类型
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiData {
    Class<? extends IApiData> clazz();
}
