package com.learkoo.social.core;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 社交平台
 * <br>
 */
public enum SOCIAL_PLATFORM {
    EMAIL("邮件"),
    SHORTMESSAE("短信息"),
    SINAWEIBO("新浪微博"),
    QZONE("QQ空间"),
    QQ("QQ"),
    WECHAT("微信"),
    WECHATMOMENTS("微信朋友圈"),
    WECHATFAVORITE("微信收藏");


    private String platform ;
    private SOCIAL_PLATFORM(String platform){
            this.platform = platform;
    }
    public String getPlatform() {
        return platform;
    }
    @Override
    public String toString() {
        return this.platform;
    }
}
