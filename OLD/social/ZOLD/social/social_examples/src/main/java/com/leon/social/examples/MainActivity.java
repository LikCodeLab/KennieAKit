package com.leon.social.examples;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leon.social.core.PlatformFactory;
import com.leon.social.core.SocialAgent;
import com.leon.social.core.impl.weixin.WXParams;
import com.leon.social.core.platform.IPlatform;
import com.leon.social.core.shareable.ShareActionListener;
import com.leon.social.core.shareable.ShareParams;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String SD_ROOT =
            android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

    private static final String IMAGE_JPG = SD_ROOT + File.separator + "emojithumb.jpg";
    private static final String IMAGE_GIF = SD_ROOT + File.separator + "emoji.gif";

    private static final String
            IMAGE_URL =
            "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20140728%2Fsl_88132874265.jpg&thumburl=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D4144427137%2C2176645001%26fm%3D21%26gp%3D0.jpg";

    private static final String
            IMAGE_EMOJI_URL =
            "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fww1.sinaimg.cn%2Flarge%2F85cccab3gw1etdkz2jm7vg205g05agqb.jpg&thumburl=http%3A%2F%2Fimg5.imgtn.bdimg.com%2Fit%2Fu%3D3078332440%2C3039432953%26fm%3D21%26gp%3D0.gif";


    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;

        setContentView(R.layout.activity_main);

        findViewById(R.id.demo_share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
//                shareWXImagePath();
//                shareWXImageEmoji();
//                shareImage();
//                shareText();
//                shareTextImage();
//                shareWebpage();
            }
        });
    }

    private void shareText() {
        final String title = "this is title";
        final String description = "this is description";

        WXText text = new WXText();
        text.shareText(title, description);
    }

    private void shareImage() {
        WXImage image = new WXImage();
        image.shareWXImageURL();
    }

    private void shareTextImage() {
        final String title = "this is title";
        final String description = "this is description";
        final String path = IMAGE_JPG;
        WXTextImage textImage = new WXTextImage();
        textImage.shareTextImage(title, description, path);
    }

    private void shareWebpage() {
        final String title = "this is title";
        final String description = "this is description";
        final String path = IMAGE_URL;
        final String url = "http://dev.hotkidclub.com/main.html";

        WXWebpage webpage = new WXWebpage();
        webpage.shareWebpage(title, description, path, url);
    }

    private class WXImage {
        void shareWXImageURL() {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .imageUrl(IMAGE_URL)
                            .type(ShareParams.Type.IMAGE))
                    .share();
        }

        void shareWXImagePath() {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.IMAGE)
                            .imagePath(IMAGE_JPG))
                    .share();
        }

        void shareWXImageEmoji() {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.EMOJ)
                            .imagePath(IMAGE_GIF))
                    .share();
        }

        void shareWXImageEmojiURL() {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.EMOJ)
                            .imageUrl(IMAGE_EMOJI_URL))
                    .share();
        }
    }

    private class WXText {
        void shareText(String title, String description) {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.TEXT)
                            .title(title)
                            .text(description))
                    .share();
        }
    }

    private class WXTextImage {
        void shareTextImage(String title, String description, String path) {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.TEXT_IMAGE)
                            .title(title)
                            .text(description)
                            .imagePath(path))
                    .share();
        }
    }

    private class WXWebpage {
        void shareWebpage(String title, String text, String path, String url) {
            SocialAgent.newInstance()
                    .platform(PlatformFactory.create(mContext, IPlatform.Name.WEIXIN_MOMENTS))
                    .shareActionListener(new ShareActionListener() {
                        @Override
                        public void onSuccess(IPlatform platform) {

                        }

                        @Override
                        public void onError(IPlatform platform, Throwable e) {

                        }

                        @Override
                        public void onCancel(IPlatform platform) {

                        }
                    })
                    .shareParams(new WXParams()
                            .type(ShareParams.Type.WEBPAGE)
                            .title(title)
                            .text(text)
                            .imagePath(path)
                            .url(url))
                    .share();
        }
    }
}
