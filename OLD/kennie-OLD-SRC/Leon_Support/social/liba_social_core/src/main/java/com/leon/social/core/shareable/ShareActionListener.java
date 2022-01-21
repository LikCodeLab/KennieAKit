package com.leon.social.core.shareable;


import com.leon.social.core.platform.IPlatform;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public interface ShareActionListener {
    /**
     * Media share success
     *
     * @param platform
     */
    void onSuccess(IPlatform platform);

    /**
     * Media share error
     *
     * @param platform
     * @param e
     */
    void onError(IPlatform platform, Throwable e);

    /**
     * User cancel
     *
     * @param platform
     */
    void onCancel(IPlatform platform);
}
