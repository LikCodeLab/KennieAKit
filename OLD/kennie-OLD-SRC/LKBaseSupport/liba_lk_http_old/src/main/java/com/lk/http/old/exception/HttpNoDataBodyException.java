package com.lk.http.old.exception;


import androidx.annotation.NonNull;

/**
 * Net数据解析异常
 */
public final class HttpNoDataBodyException extends Exception {
    public HttpNoDataBodyException(@NonNull String errerStr) {
        super(errerStr);
    }
}
