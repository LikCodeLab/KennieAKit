package com.jackylee.social.core.impl.weixin;

import android.content.Context;

import com.jackylee.social.core.Configuration;
import com.jackylee.social.core.oauthable.IOauthable;
import com.jackylee.social.core.platform.AbsPlatform;
import com.jackylee.social.core.shareable.IShareable;


/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class WXPlatform extends AbsPlatform {

    public WXPlatform(Context context, int scen, Configuration configuration) {
        this(configuration, new WXShareable(context, scen, configuration.appid), null);
    }


    protected WXPlatform(Configuration configuration, IShareable shareable,
                         IOauthable oauthable) {
        super(configuration, shareable, oauthable);
    }
}
