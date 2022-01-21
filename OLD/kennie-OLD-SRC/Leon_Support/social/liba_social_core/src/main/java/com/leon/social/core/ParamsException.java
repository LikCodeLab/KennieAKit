package com.leon.social.core;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class ParamsException extends SocialException {

    public ParamsException() {
        this("ShareParams contains error, please look at the logcat");
    }

    public ParamsException(String detailMessage) {
        super(detailMessage);
    }
}
