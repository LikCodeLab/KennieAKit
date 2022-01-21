package com.jackylee.social.core;


import com.jackylee.social.core.oauthable.IOauthParams;
import com.jackylee.social.core.oauthable.OauthActionListener;
import com.jackylee.social.core.platform.IPlatform;
import com.jackylee.social.core.shareable.ShareActionListener;
import com.jackylee.social.core.shareable.ShareParams;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class SocialAgent {

    private IPlatform mPlatform;
    private ShareParams mShareParams;
    private IOauthParams mOauthParams;
    private ShareActionListener mShareActionListener;
    private OauthActionListener mOauthActionListener;

    private SocialAgent() {
    }

    public static SocialAgent newInstance() {
        return new SocialAgent();
    }

    public SocialAgent platform(IPlatform platform) {
        this.mPlatform = platform;
        return this;
    }

    public SocialAgent shareParams(ShareParams params) {
        mPlatform.setShareParams(params);
        return this;
    }

    public SocialAgent shareActionListener(ShareActionListener listener) {
        mPlatform.setShareActionListener(listener);
        return this;
    }

    public void share() {
        mPlatform.startShare();
    }

    public SocialAgent oauthParams(IOauthParams params) {
        this.mOauthParams = params;
        return this;
    }

    public SocialAgent oauthActionListener(OauthActionListener listener) {
        this.mOauthActionListener = listener;
        return this;
    }

    public void oauth() {
        mPlatform.startOauth();
    }


}
