package com.leon.social.core;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class PlatformException extends SocialException {

    public PlatformException() {
    }

    public PlatformException(String detailMessage) {
        super(detailMessage);
    }

    public PlatformException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PlatformException(Throwable throwable) {
        super(throwable);
    }
}
