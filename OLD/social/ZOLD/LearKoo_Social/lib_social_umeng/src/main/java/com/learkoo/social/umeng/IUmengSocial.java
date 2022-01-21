package com.learkoo.social.umeng;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/03/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * <p/>share interface
 * <br>
 */
public interface IUmengSocial {

    /**
     * Init with {@link UmengShareBuilder}
     *
     * @param builder
     */
    void init(UmengShareBuilder.InnerBuilder builder);

    void share();

}
