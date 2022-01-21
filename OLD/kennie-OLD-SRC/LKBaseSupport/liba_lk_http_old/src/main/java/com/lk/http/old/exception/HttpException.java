package com.lk.http.old.exception;


import androidx.annotation.NonNull;

/**
 * 网络总异常类
 * 网络异常
 *
 * @author LK
 * @since 0.0.1
 */
public class HttpException extends Exception {

    /**
     * 异常状态码
     */
    private int code;

    /**
     * 异常消息
     */
    private String msg;

    public HttpException(@NonNull Throwable pThrowable, int pCode, @NonNull String pMsg) {
        super(pThrowable);
        this.code = pCode;
        this.msg = pMsg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
