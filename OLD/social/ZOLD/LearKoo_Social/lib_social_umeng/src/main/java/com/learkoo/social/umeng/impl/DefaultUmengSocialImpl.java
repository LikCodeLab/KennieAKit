package com.learkoo.social.umeng.impl;

import android.text.TextUtils;

import com.learkoo.social.core.IShareCallback;
import com.learkoo.social.umeng.IUmengSocial;
import com.learkoo.social.umeng.UmengShareBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;


/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 缺省的友盟分享实现类
 * <br>
 */
public class DefaultUmengSocialImpl implements IUmengSocial {
    private static  String TAG = DefaultUmengSocialImpl.class.getSimpleName();
    private UmengShareBuilder.InnerBuilder mBuilder;
    private ShareAction mShareAction;
    public DefaultUmengSocialImpl() {
    }
    @Override
    public void init(final UmengShareBuilder.InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getActivity()){
            mShareAction = new ShareAction(builder.getActivity());
        }else {
            throw new IllegalArgumentException("You must call builder.with() first!!");
        }
    }

    @Override
    public void share() {
        final IShareCallback callback = mBuilder.getCallback();
        if(null == mBuilder.getSharePlatform()){
            throw new IllegalArgumentException("You must set share platform with builder.sharePlatform() method");
        } else if (null == callback){
            throw new IllegalArgumentException(
                    "You must set share callback builder.callback() method");
        }else{
            switch (mBuilder.getSharePlatform()){
                case WECHAT:
                    //微信
                    mShareAction.setPlatform(SHARE_MEDIA.WEIXIN);
                    break;
            }
            if (!TextUtils.isEmpty(mBuilder.getTitle())){
                //分享标题
                mShareAction.withTitle(mBuilder.getTitle());
            }
            if (!TextUtils.isEmpty(mBuilder.getText())){
                //分享文本
                mShareAction.withText(mBuilder.getText());
            }
            if (!TextUtils.isEmpty(mBuilder.getUrl())){
                //分享url
                mShareAction.withTargetUrl(mBuilder.getUrl());
            }
            if (null != mBuilder.getUmImage()){
                //分享图片
                mShareAction.withMedia(mBuilder.getUmImage());
            }
            mShareAction.setCallback(new UMShareListener() {
                        @Override
                        public void onResult(SHARE_MEDIA share_media) {
                            mBuilder.getCallback().onSuccess(mBuilder.getSharePlatform() , "");
                        }
                        @Override
                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                            mBuilder.getCallback().onError(mBuilder.getSharePlatform() ,"");
                        }
                        @Override
                        public void onCancel(SHARE_MEDIA share_media) {
                            mBuilder.getCallback().onCancel(mBuilder.getSharePlatform() , "");
                        }
                    });
                mShareAction.share();
        }


    }

}
