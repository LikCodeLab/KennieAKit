package com.want.social;

import com.want.social.oauthable.IOauthParams;
import com.want.social.oauthable.OauthActionListener;
import com.want.social.platform.IPlatform;
import com.want.social.shareable.ShareActionListener;
import com.want.social.shareable.ShareParams;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
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
