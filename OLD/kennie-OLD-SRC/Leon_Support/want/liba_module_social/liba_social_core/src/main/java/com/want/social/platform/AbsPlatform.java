package com.want.social.platform;

import com.want.social.Configuration;
import com.want.social.oauthable.IOauthParams;
import com.want.social.oauthable.IOauthable;
import com.want.social.oauthable.OauthActionListener;
import com.want.social.shareable.IShareable;
import com.want.social.shareable.ShareActionListener;
import com.want.social.shareable.ShareParams;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
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
