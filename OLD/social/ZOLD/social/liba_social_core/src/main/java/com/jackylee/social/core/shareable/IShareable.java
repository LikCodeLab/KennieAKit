package com.jackylee.social.core.shareable;


import com.jackylee.social.core.platform.IPlatform;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public interface IShareable {

    /**
     * 分享调用用口函数
     *
     * @param params
     */
    void share(ShareParams params);

    /**
     * 分享平台执行分享过程
     */
    void doShare();

    <T> T getShareParams();

    void setShareActionListener(ShareActionListener listener);

    void setPlatform(IPlatform platform);

}
