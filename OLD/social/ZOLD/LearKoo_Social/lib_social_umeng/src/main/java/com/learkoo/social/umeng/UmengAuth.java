package com.learkoo.social.umeng;

import android.app.Activity;
import android.content.Context;

import com.learkoo.social.core.IAuthCallback;
import com.learkoo.social.core.SOCIAL_PLATFORM;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/03/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * <p/>auth
 * 友盟第三方登录
 * <br>
 */
public class UmengAuth implements IUmengAuth{

    private UMShareAPI mUmShareAPI;
    private static UmengAuth mInstance;
    private Context mContext ;
    private Activity mActivity ;
    private boolean isAuth = false; //未授权获取用户信息入口授权页面

    public UmengAuth(Activity activity) {
        mUmShareAPI = UMShareAPI.get(activity);
        mContext = activity;
        mActivity = activity;
    }

    public static UmengAuth get(Activity activity) {
        if (null == mInstance) {
            synchronized (UmengAuth.class) {
                if (null == mInstance) {
                    mInstance = new UmengAuth(activity);
                }
            }
        }
        return mInstance;
    }

    private UMShareAPI getmUmShareAPI() {
        return mUmShareAPI;
    }

    /**
     * 判断授权平台是否安装
     * @param platform 授权平台
     * @return
     */
    @Override
    public boolean isInstall(SOCIAL_PLATFORM platform) {
        if (platform == SOCIAL_PLATFORM.WECHAT){
            return mUmShareAPI.isInstall(mActivity ,SHARE_MEDIA.WEIXIN);
        }else{
            return false;
        }
    }

    @Override
    public boolean isAuthorize(SOCIAL_PLATFORM platform) {
        if (platform == SOCIAL_PLATFORM.WECHAT){
            return mUmShareAPI.isAuthorize(mActivity,SHARE_MEDIA.WEIXIN);
        }else{
            return false;
        }

    }
    @Override
    public void auth(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback) {
            SHARE_MEDIA share_media = null ;
            switch (sharePlatform){
                case WECHAT:
                    share_media = SHARE_MEDIA.WEIXIN;
                    break;
            }
        mUmShareAPI.doOauthVerify(mActivity, share_media, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> map) {
                if (isAuth){
                    getPlatformInfo(sharePlatform , authCallback);
                }else {
                    authCallback.onSuccess(sharePlatform,mContext.getString(R.string.social_auth_success) , map);
                }
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable throwable) {
                authCallback.onError(sharePlatform, mContext.getString(R.string.social_auth_fail));

            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                authCallback.onCancel(sharePlatform , mContext.getString(R.string.social_auth_cancel));

            }
        });

    }

    @Override
    public void delAuth(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback) {
        SHARE_MEDIA share_media = null ;
        switch (sharePlatform){
            case WECHAT:
                share_media = SHARE_MEDIA.WEIXIN;
                break;
        }
        mUmShareAPI.deleteOauth(mActivity, share_media, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int i, Map<String, String> map) {
                authCallback.onSuccess(sharePlatform , mContext.getString(R.string.social_del_auth_success),map);
            }

            @Override
            public void onError(SHARE_MEDIA platform, int i, Throwable throwable) {
                authCallback.onError(sharePlatform, mContext.getString(R.string.social_del_auth_fail));

            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int i) {
                authCallback.onCancel(sharePlatform , mContext.getString(R.string.social_del_auth_cancel));
            }
        });
    }

    @Override
    public void getPlatformInfo(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback) {
        if (isAuthorize(sharePlatform)){
            isAuth = false;
            SHARE_MEDIA share_media = null ;
            switch (sharePlatform){
                case WECHAT:
                    share_media = SHARE_MEDIA.WEIXIN;
                    break;
            }
            mUmShareAPI.getPlatformInfo(mActivity, share_media, new UMAuthListener() {
                @Override
                public void onComplete(SHARE_MEDIA platform, int i, Map<String, String> map) {
                    authCallback.onSuccess(sharePlatform , mContext.getString(R.string.social_get_auth_info_success) , map);
                }

                @Override
                public void onError(SHARE_MEDIA platform, int i, Throwable throwable) {
                    authCallback.onError(sharePlatform, mContext.getString(R.string.social_get_auth_info_fail));
                }

                @Override
                public void onCancel(SHARE_MEDIA platform, int i) {
                    authCallback.onCancel(sharePlatform , mContext.getString(R.string.social_get_auth_info_cancel));
                }
            });
        }else{
            isAuth = true;
            auth(sharePlatform ,authCallback);
        }
    }

}
