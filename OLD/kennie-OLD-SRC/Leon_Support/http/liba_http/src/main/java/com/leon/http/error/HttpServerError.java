package com.leon.http.error;

/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/7/27<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * Http server error
 * <br>
 */
public class HttpServerError extends HttpError {
    public HttpServerError() {
    }

    public HttpServerError(Throwable cause) {
        super(cause);
    }
}
