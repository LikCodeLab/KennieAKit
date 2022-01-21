package com.learkoo.social.sharesdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.learkoo.social.core.IShareCallback;
import com.learkoo.social.core.SOCIAL_PLATFORM;
import com.learkoo.social.sharesdk.impl.DefaultShareSDKSocialImpl;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * ShareSDK build
 * ShareParams字段释义:http://wiki.mob.com/%E5%88%86%E4%BA%AB%E5%88%B0%E6%8C%87%E5%AE%9A%E5%B9%B3%E5%8F%B0/#h1-1
 * Android 不同平台分享内容的详细说明:http://wiki.mob.com/%E4%B8%8D%E5%90%8C%E5%B9%B3%E5%8F%B0%E5%88%86%E4%BA%AB%E5%86%85%E5%AE%B9%E7%9A%84%E8%AF%A6%E7%BB%86%E8%AF%B4%E6%98%8E/
 * <br>
 */
public class ShareSDKShareBuilder {
    private static String TAG = ShareSDKShareBuilder.class.getSimpleName();
    private static Builder sDefaultBuilder;
    private InnerBuilder mBuilder;
    private IShareSDKSocial shareSDKSocial;

    private ShareSDKShareBuilder(InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getShareSDKSocial()) {
            this.shareSDKSocial = builder.getShareSDKSocial();
        } else {
            this.shareSDKSocial = new DefaultShareSDKSocialImpl();
        }
    }

    /**
     * 启动分享(必须调用)
     */
    public void share(){
        shareSDKSocial.init(mBuilder);
        shareSDKSocial.share();
    }

    /**
     * Wrap your attrs to this builder
     */
    public static class Builder {
        private IShareSDKSocial shareSDKSocial;
        private Activity withActivity;
        private Context withContext;
        private SOCIAL_PLATFORM sharePlatform;//分享平台
        private String title;//分享标题
        private String titleUrl; //分享标题url
        private String text;//分享文本内容
        private String site; //分享此内容的网站名称(仅在QQ空间使用)
        private String siteUrl; //分享此内容的网站地址(仅在QQ空间使用)
        private String imageUrl; //图片URL
        private String imagePath;//图片路径
        private Bitmap imageData;//
        private String url;
        private int shareType;//微信分享类型
        private String musicUrl;//
        private String filePath;//
        private String address;//
        private IShareCallback callback; //回调

        public Builder() {
            this(sDefaultBuilder);
        }

        private Builder(final Builder builder) {
            if (null != builder) {
                this.shareSDKSocial = builder.shareSDKSocial;
                this.withActivity = builder.withActivity;
                this.withContext = builder.withContext;
                this.sharePlatform = builder.sharePlatform;
                this.title = builder.title;
                this.titleUrl = builder.titleUrl;
                this.text = builder.text;
                this.site = builder.site;
                this.siteUrl = builder.siteUrl;
                this.imageUrl = builder.imageUrl;
                this.imagePath = builder.imagePath;
                this.imageData = builder.imageData;
                this.url = builder.url;
                this.shareType = builder.shareType;
                this.musicUrl = builder.musicUrl;
                this.filePath = builder.filePath;
                this.address = builder.address;
                this.callback = builder.callback;
            }
        }

        public ShareSDKShareBuilder build() {
            return new ShareSDKShareBuilder(new InnerBuilder(this));
        }

        /**
         * Set the {@link IShareSDKSocial}
         *
         * @param shareSDKSocial {@link IShareSDKSocial}
         * @return {@link Builder}
         */
        private Builder umengSocial(IShareSDKSocial shareSDKSocial) {
            this.shareSDKSocial = shareSDKSocial;
            return this;
        }

        /**
         * Context is {@link Activity}
         *
         * @param activity activity
         * @return {@link Builder}
         */
        public Builder with(Activity activity) {
            this.withActivity = activity;
            return this;
        }

        /**
         * Context is {@link Activity}
         *
         * @param context activity
         * @return {@link Builder}
         */
        public Builder with(Context context) {
            this.withContext = context;
            return this;
        }

        /**
         * 分享平台
         * sharePlatform {@link SOCIAL_PLATFORM}
         *
         * @param sharePlatform
         * @return {@link Builder}
         */
        public Builder sharePlatform(SOCIAL_PLATFORM sharePlatform) {
            this.sharePlatform = sharePlatform;
            return this;
        }

        /**
         *
         * @param title 分享内容的标题
         * @return
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         *
         * @param titleUrl 分享内容标题的链接地址
         * @return
         */
        public Builder titleUrl(String titleUrl) {
            this.titleUrl = titleUrl;
            return this;
        }

        /**
         *
         * @param text 待分享的文本
         * @return
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         *
         * @param site QQ空间的字段，标记分享应用的名称
         * @return
         */
        public Builder site(String site) {
            this.site = site;
            return this;
        }

        /**
         *
         * @param siteUrl  QQ空间的字段，标记分享应用的网页地址
         * @return
         */
        public Builder siteUrl(String siteUrl) {
            this.siteUrl = siteUrl;
            return this;
        }

        /**
         *
         * @param imageUrl  待分享的网络图片
         * @return
         */
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         *
         * @param imagePath 待分享的本地图片。如果目标平台使用客户端分享，此路径不可以在/data/data下面
         * @return
         */
        public Builder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        /**
         *
         * @param imageData 微信和易信的字段，各类分享内容中的图片bitmap对象，可以替代imagePath或者imageUrl
         * @return
         */
        public Builder imageData(Bitmap imageData) {
            this.imageData = imageData;
            return this;
        }

        /**
         *
         * @param url 分享内容的url、在微信和易信中也使用为视频文件地址
         * @return
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         *
         * @param shareType 微信和易信的字段，分享内容的类型
         *                  Platform.SHARE_TEXT（分享文本）
         *                  Platform.SHARE_IMAGE（分享图片）
         *                  Platform.SHARE_WEBPAGE（分享网页，既图文分享）
         *                  Platform.SHARE_MUSIC（分享音频）
         *                  Platform.SHARE_VIDEO（分享视频）
         *                  Platform.SHARE_APPS（分享应用，仅微信支持）
         *                  Platform.SHARE_FILE（分享文件，仅微信支持）
         *                  Platform.SHARE_EMOJI（分享表情，仅微信支持）
         * @return
         */
        public Builder shareType(int shareType) {
            this.shareType = shareType;
            return this;
        }

        /**
         *
         * @param musicUrl 微信和易信的字段，分享音频时的音频文件网络地址
         * @return
         */
        public Builder musicUrl(String musicUrl) {
            this.musicUrl = musicUrl;
            return this;
        }

        /**
         *
         * @param filePath 待分享的文件路径。这个用在Dropbox和Wechat中
         * @return
         */
        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        /**
         *
         * @param address 邮箱地址或者短信电话号码，一般在邮箱或者短信中使用
         * @return
         */
        public Builder address(String address) {
            this.address = address;
            return this;
        }


        /**
         * 分享回调
         * {@link IShareCallback}
         *
         * @param callback callback
         * @return {@link Builder}
         */
        public Builder callback(IShareCallback callback) {
            this.callback = callback;
            return this;
        }

    }

    public static class InnerBuilder {
        private Builder builder;

        public InnerBuilder(Builder builder) {
            this.builder = builder;
        }

        public IShareSDKSocial getShareSDKSocial() {
            return builder.shareSDKSocial;
        }
        public Activity getActivity() {
            return builder.withActivity;
        }
        public Context getContext() {
            return builder.withContext;
        }
        public SOCIAL_PLATFORM getSharePlatform(){ return builder.sharePlatform;}
        public String getTitle(){return  builder.title;}
        public String getTitleUrl(){return  builder.titleUrl;}
        public String getText(){return  builder.text;}
        public String getSite(){return  builder.site;}
        public String getSiteUrl(){return  builder.siteUrl;}
        public String getImageUrl(){return  builder.imageUrl;}
        public String getImagePath(){return  builder.imagePath;}
        public Bitmap getImageData(){return  builder.imageData;}
        public String getUrl(){return  builder.url;}
        public int getShareType(){return builder.shareType;}
        public String getMusicUrl(){return  builder.musicUrl;}
        public String getfilePath(){return  builder.filePath;}
        public String getAddress(){return  builder.address;}
        public IShareCallback getCallback() {
            return builder.callback;
        }
    }
}
