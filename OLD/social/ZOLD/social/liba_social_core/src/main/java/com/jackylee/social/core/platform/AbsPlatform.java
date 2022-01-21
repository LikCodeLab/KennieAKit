package com.jackylee.social.core.platform;


import com.jackylee.social.core.Configuration;
import com.jackylee.social.core.oauthable.IOauthParams;
import com.jackylee.social.core.oauthable.IOauthable;
import com.jackylee.social.core.oauthable.OauthActionListener;
import com.jackylee.social.core.shareable.IShareable;
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
public abstract class AbsPlatform implements IPlatform {

    private IShareable mShareable;
    private ShareParams mShareParams;

    private IOauthable mOauthable;
    private IOauthParams mOauthParams;

    protected AbsPlatform(Configuration configuration, IShareable shareable, IOauthable oauthable) {
        this.mShareable = shareable;
        this.mOauthable = oauthable;

        mShareable.setPlatform(this);
    }

    @Override
    public void setShareParams(ShareParams params) {
        this.mShareParams = params;
    }

    @Override
    public void setShareActionListener(ShareActionListener listener) {
        mShareable.setShareActionListener(listener);
    }

    @Override
    public void startShare() {
        mShareable.share(mShareParams);
    }

    @Override
    public void startOauth() {
        mOauthable.auth(mOauthParams);
    }

    @Override
    public void setOauthParams(IOauthParams params) {
        this.mOauthParams = params;
    }

    @Override
    public void setOauthActionListener(OauthActionListener listener) {
        mOauthable.setOauthActionListener(listener);
    }
}
