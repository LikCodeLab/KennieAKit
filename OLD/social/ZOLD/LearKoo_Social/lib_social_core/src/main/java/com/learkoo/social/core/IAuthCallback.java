package com.learkoo.social.core;

import java.util.Map;


/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 第三方登录认证授权和取消回调
 * <br>
 */
public interface IAuthCallback {
    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 描述信息 (认证成功信息)
     * @param data 成功返回信息
     */
    void onSuccess(SOCIAL_PLATFORM platform, String message, Map<String, String> data);

    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 描述信息  (认证失败异常信息)
     */
    void onError(SOCIAL_PLATFORM platform, String message);

    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 信息 (取消认证失败信息)
     */
    void onCancel(SOCIAL_PLATFORM platform, String message);
}
