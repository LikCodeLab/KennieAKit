package com.want.base.http.error;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/7/9<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Http request error
 * <br>
 */
public class HttpError extends Exception {
    private long networkTimeMs;

    public HttpError() {
    }

    public HttpError(String exceptionMessage) {
        super(exceptionMessage);
    }

    public HttpError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
    }

    public HttpError(Throwable cause) {
        super(cause);
    }

    public void setNetworkTimeMs(long networkTimeMs) {
        this.networkTimeMs = networkTimeMs;
    }

    public long getNetworkTimeMs() {
        return networkTimeMs;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
