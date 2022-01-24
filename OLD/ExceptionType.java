package com.kennie.http.exception;

/**
 * Author：Kennie
 * Project：KennieHttp
 * Class：ExceptionType
 * Date：2021/12/12 23:15
 * Desc：异常分类枚举 SERVER 服务器异常 API 接口异常 UNKNOWN 未知异常
 */
public enum ExceptionType {

    /**
     * SERVER: 服务器异常
     * CONNECT: 服务器连接异常（有网环境，接口超时，SSL 错误等）
     * JSON_PARSE: 服务器返回数据解析为本地数据结构出错
     * API: 接口返回的定义异常
     * LOCAL：本地处理抛出异常
     * UNKNOWN: 未知异常
     * NETWORK：网络异常 (废弃)
     */
    SERVER, CONNECT, JSON_PARSE, API, LOCAL, UNKNOWN, @Deprecated NETWORK
}
