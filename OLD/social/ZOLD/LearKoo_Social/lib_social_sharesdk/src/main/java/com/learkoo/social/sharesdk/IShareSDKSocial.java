package com.learkoo.social.sharesdk;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/03/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * <p/>share interface
 * <br>
 */
public interface IShareSDKSocial {

    /**
     * Init with {@link ShareSDKShareBuilder}
     *
     * @param builder
     */
    void init(ShareSDKShareBuilder.InnerBuilder builder);

    void share();

}
