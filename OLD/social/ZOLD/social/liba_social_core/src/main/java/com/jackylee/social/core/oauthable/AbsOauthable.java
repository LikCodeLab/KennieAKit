package com.jackylee.social.core.oauthable;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public abstract class AbsOauthable implements IOauthable {


    @Override
    public void auth(IOauthParams params) {

    }

    @Override
    public IOauthParams getOauthParams() {
        return null;
    }

    @Override
    public void setOauthActionListener(OauthActionListener listener) {

    }
}
