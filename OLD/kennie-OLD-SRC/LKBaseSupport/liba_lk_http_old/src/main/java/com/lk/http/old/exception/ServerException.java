package com.lk.http.old.exception;

public class ServerException extends RuntimeException {

    /**
     * 异常状态码
     */
    private int errCode;

    /**
     * 异常消息
     */
    private String errMsg;


    public ServerException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    /**
     * 返回异常状态码
     *
     * @return 异常状态码 异常状态码 -> 系统异常
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * 返回异常消息str
     *
     * @return 异常消息str
     */
    @Override
    public String getMessage() {
        return errMsg;
    }

}
