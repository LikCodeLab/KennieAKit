package com.leon.http.error;

import android.content.Intent;

/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/7/27<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b> <br>
 */
public class HttpAuthFailureError extends HttpError {
    /** An intent that can be used to resolve this exception. (Brings up the password dialog.) */
    private Intent mResolutionIntent;

    public HttpAuthFailureError() { }

    public HttpAuthFailureError(Intent intent) {
        mResolutionIntent = intent;
    }

    public HttpAuthFailureError(String message) {
        super(message);
    }

    public HttpAuthFailureError(String message, Exception reason) {
        super(message, reason);
    }

    public Intent getResolutionIntent() {
        return mResolutionIntent;
    }

    @Override
    public String getMessage() {
        if (mResolutionIntent != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
    
    
}
