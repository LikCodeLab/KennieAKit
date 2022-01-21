package com.jackylee.social.core;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class SocialException extends Exception {
    public SocialException() {
    }

    public SocialException(String detailMessage) {
        super(detailMessage);
    }

    public SocialException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SocialException(Throwable throwable) {
        super(throwable);
    }
}
