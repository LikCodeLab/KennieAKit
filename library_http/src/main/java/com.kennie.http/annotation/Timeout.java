package com.kennie.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description : 单个接口注解添加超时时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timeout {

    int connectTimeout() default 20000;

    int readTimeout() default 20000;

    int writeTimeout() default 20000;
}
