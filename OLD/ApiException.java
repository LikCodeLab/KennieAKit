package com.kennie.http.exception;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.JsonParseException;
import com.kennie.http.HttpCode;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * Author：Kennie
 * Project：KennieHttp
 * Class：ExceptionType
 * Date：2021/12/12 23:15
 * Desc：API异常统一管理
 */
public class ApiException extends IOException {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误消息
     */
    private String message;

    private String data;

    /**
     * 错误类型
     */
    private ExceptionType exceptionType = ExceptionType.UNKNOWN;


    public ApiException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ApiException(int code, String msg, String data) {
        this.code = code;
        this.data = data;
        this.message = msg;
    }


    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(httpException.code(), e);
            switch (httpException.code()) {
                case HttpCode.HTTP.CREATED:
                case HttpCode.HTTP.RECEIVED:
                case HttpCode.HTTP.UN_AUTH_CONTENT:
                case HttpCode.HTTP.NONE_CONTENT:
                case HttpCode.HTTP.RESET:
                case HttpCode.HTTP.PART_RESOLVED:
                case HttpCode.HTTP.ERROR:
                case HttpCode.HTTP.UNAUTHORIZED:
                case HttpCode.HTTP.REFUSE:
                case HttpCode.HTTP.NOT_FOUND:
                case HttpCode.HTTP.METHOD_REFUSE:
                case HttpCode.HTTP.CAN_NOT_RECEIVE:
                case HttpCode.HTTP.WAIT_TIME_OUT:
                case HttpCode.HTTP.INNER_EXCEPTION:
                case HttpCode.HTTP.UM_IMPLEMENTS:
                case HttpCode.HTTP.GATEWAY_EXCEPTION:
                case HttpCode.HTTP.SERVICE_UNUSEFUL:
                case HttpCode.HTTP.GATEWAY_TIME_OUT:
                case HttpCode.HTTP.HTTP_VERSION_UN_SUPPORT:
                default:
                    ex.setExceptionType(ExceptionType.SERVER);
                    ex.setMessage(e.getMessage());
                    break;
            }
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(HttpCode.CODE.NETWORK_ERROR, e);
            ex.setExceptionType(ExceptionType.CONNECT);
            ex.setMessage("服务器连接异常，请稍后再试");
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new ApiException(HttpCode.CODE.SSL_ERROR, e);
            ex.setExceptionType(ExceptionType.CONNECT);
            ex.setMessage("SSL_ERROR");
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(HttpCode.CODE.TIMEOUT_ERROR, e);
            ex.setExceptionType(ExceptionType.CONNECT);
            ex.setMessage("服务器请求超时，请稍后再试");
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ApiException(HttpCode.CODE.PARSE_ERROR, e);
            ex.message = e.getMessage();
            ex.setExceptionType(ExceptionType.JSON_PARSE);
            return ex;
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            exception.setExceptionType(ExceptionType.API);
            return exception;
        } else {
            ex = new ApiException(HttpCode.CODE.UNKNOWN, e);
            ex.setMessage("UNKNOWN");
            ex.setExceptionType(ExceptionType.UNKNOWN);
            return ex;
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getData() {
        return TextUtils.isEmpty(data) ? "" : data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", exceptionType=" + exceptionType +
                '}';
    }
}
