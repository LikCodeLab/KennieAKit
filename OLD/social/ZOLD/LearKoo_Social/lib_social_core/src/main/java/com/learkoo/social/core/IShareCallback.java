package com.learkoo.social.core;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 统一分享回调
 * <br>
 */
public interface IShareCallback {

    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 描述信息 (成功)
     */
    void onSuccess(SOCIAL_PLATFORM platform, String message);

    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 描述信息 (失败)
     */
    void onError(SOCIAL_PLATFORM platform, String message);

    /**
     *
     * @param platform 平台 {@link SOCIAL_PLATFORM}
     * @param message 描述信息 (取消)
     */
    void onCancel(SOCIAL_PLATFORM platform, String message);
}
