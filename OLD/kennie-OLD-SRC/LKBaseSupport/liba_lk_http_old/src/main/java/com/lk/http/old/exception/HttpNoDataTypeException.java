package com.lk.http.old.exception;


import androidx.annotation.NonNull;

/**
 * Net数据无匹配类型异常
 */
public final class HttpNoDataTypeException extends Exception {
    public HttpNoDataTypeException(@NonNull String errerStr) {
        super(errerStr);
    }
}
