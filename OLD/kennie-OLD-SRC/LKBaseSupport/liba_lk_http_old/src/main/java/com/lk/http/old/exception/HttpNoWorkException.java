package com.lk.http.old.exception;


import androidx.annotation.NonNull;

import java.io.IOException;

/**
 * Net响应异常
 */
public final class HttpNoWorkException extends IOException {
    public HttpNoWorkException(@NonNull String errerStr) {
        super(errerStr);
    }
}
