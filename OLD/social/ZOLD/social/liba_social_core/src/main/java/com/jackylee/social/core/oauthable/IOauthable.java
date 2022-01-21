package com.jackylee.social.core.oauthable;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public interface IOauthable {

    void auth(IOauthParams params);

    void doAuthorize();

    IOauthParams getOauthParams();

    void setOauthActionListener(OauthActionListener listener);
}
