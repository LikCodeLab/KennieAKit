package com.learkoo.social.umeng;


import com.learkoo.social.core.IAuthCallback;
import com.learkoo.social.core.SOCIAL_PLATFORM;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/03/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * <p/>auth interface
 * <br>
 */
public interface IUmengAuth {



    /**
     * 判断授权平台是否安装
     * @param platform 授权平台
     * @return true 已安装 false 未安装
     */
    boolean isInstall(SOCIAL_PLATFORM platform);

    /**
     * 判断平台是否授权认证
     * @param platform 授权平台
     * @return true 已安装 false 未安装
     */
    boolean isAuthorize(SOCIAL_PLATFORM platform);



    /**
     * 授权认证入口
     * @param sharePlatform 认证平台
     * @param authCallback 认证回调
     */
    void auth(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback);

    /**
     * 删除认证
     * @param sharePlatform 认证平台
     * @param authCallback 认证回调
     */
    void delAuth(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback);

    /**
     * 获取授权用户信息
     * @param sharePlatform 认证平台
     * @param authCallback 授权认证用户信息回调
     */
    void getPlatformInfo(final SOCIAL_PLATFORM sharePlatform, final IAuthCallback authCallback);

}
