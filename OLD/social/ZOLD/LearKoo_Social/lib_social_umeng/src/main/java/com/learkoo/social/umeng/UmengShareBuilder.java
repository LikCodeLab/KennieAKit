package com.learkoo.social.umeng;

import android.app.Activity;

import com.learkoo.social.core.IShareCallback;
import com.learkoo.social.core.SOCIAL_PLATFORM;
import com.learkoo.social.umeng.impl.DefaultUmengSocialImpl;
import com.umeng.socialize.media.UMImage;


/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/24<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 友盟分享build
 * <br>
 */
public class UmengShareBuilder {
    private static String TAG = UmengShareBuilder.class.getSimpleName();
    private static Builder sDefaultBuilder;
    private InnerBuilder mBuilder;
    private IUmengSocial umengSocial;

    private UmengShareBuilder(InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getUmengSocial()) {
            this.umengSocial = builder.getUmengSocial();
        } else {
            this.umengSocial = new DefaultUmengSocialImpl();
        }
    }

    /**
     * 启动分享(必须调用)
     */
    public void share(){
        umengSocial.init(mBuilder);
        umengSocial.share();
    }

    /**
     * Config the default {@link Builder}
     *
     * @param builder {@link Builder}
     */
    private static void defaultBuilder(Builder builder) {
        UmengShareBuilder.sDefaultBuilder = builder;
    }
    /**
     * Wrap your attrs to this builder
     */
    public static class Builder {
        private IUmengSocial umengSocial;
        private Activity withActivity;
        private SOCIAL_PLATFORM sharePlatform;//分享平台
        private String title ;//分享标题
        private String text ;//分享文本内容
        private String url ; //分享URL
        private UMImage umImage ; //图片分享
        private IShareCallback callback; //回调

        public Builder() {
            this(sDefaultBuilder);
        }

        private Builder(final Builder builder) {
            if (null != builder) {
                this.umengSocial = builder.umengSocial;
                this.withActivity = builder.withActivity;
                this.sharePlatform = builder.sharePlatform;
                this.title = builder.title;
                this.text = builder.text;
                this.url = builder.url;
                this.umImage = builder.umImage;
                this.callback = builder.callback;
            }
        }

        public UmengShareBuilder build() {
            return new UmengShareBuilder(new InnerBuilder(this));
        }

        /**
         * Set the {@link IUmengSocial}
         *
         * @param umengSocial {@link IUmengSocial}
         *
         * @return {@link Builder}
         */
        private Builder umengSocial(IUmengSocial umengSocial) {
            this.umengSocial = umengSocial;
            return this;
        }

        /**
         * Context is {@link Activity}
         *
         * @param activity activity
         *
         * @return {@link Builder}
         */
        public Builder with(Activity activity) {
            this.withActivity = activity;
            return this;
        }

        /**
         * 分享平台
         * sharePlatform {@link SOCIAL_PLATFORM}
         * @param sharePlatform
         * @return {@link Builder}
         */
        public Builder sharePlatform(SOCIAL_PLATFORM sharePlatform){
            this.sharePlatform = sharePlatform;
            return this;
        }

        /**
         * 分享标题
         * title {@link String}
         * @param title
         * @return {@link Builder}
         */
        public Builder title(String title){
            this.title = title;
            return this;
        }

        /**
         * 分享文本内容
         * text {@link String}
         * @param text
         * @return {@link Builder}
         */
        public Builder text(String text){
            this.text = text;
            return this;
        }

        /**
         * 分享URL
         * url {@link String}
         * @param url
         * @return {@link Builder}
         */
        public Builder url(String url){
            this.url = url;
            return this;
        }

        /**
         * 分享图片URL
         * url {@link UMImage}
         * @param umImage
         * @return {@link Builder}
         */
        public Builder umImage(UMImage umImage){
            this.umImage = umImage;
            return this;
        }

        /**
         * 分享回调
         * {@link IShareCallback}
         *
         * @param callback callback
         *
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
        public IUmengSocial getUmengSocial() {
            return builder.umengSocial;
        }
        public Activity getActivity() {
            return builder.withActivity;
        }
        public SOCIAL_PLATFORM getSharePlatform(){ return builder.sharePlatform;}
        public String getTitle(){return  builder.title;}
        public String getText(){return  builder.text;}
        public String getUrl(){return  builder.url;}
        public UMImage getUmImage(){return  builder.umImage;}
        public IShareCallback getCallback() {
            return builder.callback;
        }
    }
}
