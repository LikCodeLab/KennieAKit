package com.learkoo.social.sharesdk.impl;

import android.text.TextUtils;

import com.learkoo.social.core.IShareCallback;
import com.learkoo.social.sharesdk.IShareSDKSocial;
import com.learkoo.social.sharesdk.ShareSDKShareBuilder;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * <b>Project:</b> LearKooEngine<br>
 * <b>Create Date:</b> 16/3/30<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * <br>
 */
public class DefaultShareSDKSocialImpl implements IShareSDKSocial{
    private static  String TAG = DefaultShareSDKSocialImpl.class.getSimpleName();
    private ShareSDKShareBuilder.InnerBuilder mBuilder;
    private ShareParams shareParams;
    private Platform platform;
    public DefaultShareSDKSocialImpl() {
    }

    @Override
    public void init(final ShareSDKShareBuilder.InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getActivity()){
            ShareSDK.initSDK(builder.getActivity());
            shareParams = new ShareParams();
        }else if (null != builder.getContext()){
            ShareSDK.initSDK(builder.getContext());
            shareParams = new ShareParams();
        }else{
            throw new IllegalArgumentException("You must call builder.with() first!!");

        }
    }

    @Override
    public void share() {
        final IShareCallback callback = mBuilder.getCallback();
        if(null == mBuilder.getSharePlatform()){
            throw new IllegalArgumentException("You must set share platform with builder.sharePlatform() method");
        } else if (null == callback){
            throw new IllegalArgumentException(
                    "You must set share callback builder.callback() method");
        }else{
            shareParams.setTitle(mBuilder.getTitle());
            shareParams.setText(mBuilder.getText());//可选参数
            if (!TextUtils.isEmpty(mBuilder.getImageUrl())){
                shareParams.setImageUrl(mBuilder.getImageUrl());
            }else if(!TextUtils.isEmpty(mBuilder.getImagePath())){
                shareParams.setImagePath(mBuilder.getImagePath());
            }else if(null != mBuilder.getImageData()){
                shareParams.setImageData(mBuilder.getImageData());
            }
            switch (mBuilder.getSharePlatform()){
                case WECHAT:
                    //微信分享
                    //微信朋友圈分享(朋友圈不能分享表情、文件和应用)
                    //微信收藏分享(收藏不能分享表情和应用)
                    //参数说明 title：512Bytes以内 text：1KB以内 imageData：10M以内
                    // imagePath：10M以内(传递的imagePath路径不能超过10KB)
                    // imageUrl：10KB以内 musicUrl：10KB以内 url：10KB以内
                    //微信并无实际的分享网络图片和分享bitmap的功能，如果设置了网络图片，
                    // 此图片会先下载会本地，之后再当作本地图片分享，因此延迟较大。bitmap则好一些，
                    // 但是由于bitmap并不知道图片的格式，因此都会被当作png编码，再提交微信客户端。
                    // 此外，SHARE_EMOJI支持gif文件，但是如果使用imageData，则默认只是提交一个png图片，
                    // 因为bitmap是静态图片。
                    platform = ShareSDK.getPlatform(Wechat.NAME);
                    if (mBuilder.getShareType() <= 0) shareParams.setShareType(Platform.SHARE_TEXT);
                    if (mBuilder.getShareType() == Platform.SHARE_TEXT){
                        //分享文本
                        shareParams.setShareType(Platform.SHARE_TEXT);
                    }else if(mBuilder.getShareType() == Platform.SHARE_IMAGE){
                        //分享图片
                        shareParams.setShareType(Platform.SHARE_IMAGE);
                    }else if(mBuilder.getShareType() == Platform.SHARE_MUSIC){
                        //分享音乐
                        shareParams.setShareType(Platform.SHARE_MUSIC);
                        shareParams.setMusicUrl(mBuilder.getMusicUrl());
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_VIDEO){
                        //分享视频
                        shareParams.setShareType(Platform.SHARE_VIDEO);
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_WEBPAGE){
                        //分享网页
                        shareParams.setShareType(Platform.SHARE_WEBPAGE);
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_APPS){
                        //分享应用
                        shareParams.setShareType(Platform.SHARE_APPS);
                        shareParams.setFilePath(mBuilder.getfilePath());
                    }else if(mBuilder.getShareType() == Platform.SHARE_FILE){
                        //分享文件
                        shareParams.setShareType(Platform.SHARE_FILE);
                        shareParams.setFilePath(mBuilder.getfilePath());
                    }else if(mBuilder.getShareType() == Platform.SHARE_EMOJI){
                        //分享表情
                        shareParams.setShareType(Platform.SHARE_EMOJI);
                    }
                    break;
                case WECHATMOMENTS:
                    platform = ShareSDK.getPlatform(WechatMoments.NAME);
                    if (mBuilder.getShareType() <= 0) shareParams.setShareType(Platform.SHARE_TEXT);
                    if (mBuilder.getShareType() == Platform.SHARE_TEXT){
                        //分享文本
                        shareParams.setShareType(Platform.SHARE_TEXT);
                    }else if(mBuilder.getShareType() == Platform.SHARE_IMAGE){
                        //分享图片
                        shareParams.setShareType(Platform.SHARE_IMAGE);
                    }else if(mBuilder.getShareType() == Platform.SHARE_MUSIC){
                        //分享音乐
                        shareParams.setShareType(Platform.SHARE_MUSIC);
                        shareParams.setMusicUrl(mBuilder.getMusicUrl());
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_VIDEO){
                        //分享视频
                        shareParams.setShareType(Platform.SHARE_VIDEO);
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_WEBPAGE){
                        //分享网页
                        shareParams.setShareType(Platform.SHARE_WEBPAGE);
                        shareParams.setUrl(mBuilder.getUrl());
                    }
                    break;
                case WECHATFAVORITE:
                    platform = ShareSDK.getPlatform(WechatFavorite.NAME);
                    if (mBuilder.getShareType() <= 0) shareParams.setShareType(Platform.SHARE_TEXT);
                    if (mBuilder.getShareType() == Platform.SHARE_TEXT){
                        //分享文本
                        shareParams.setShareType(Platform.SHARE_TEXT);
                    }else if(mBuilder.getShareType() == Platform.SHARE_IMAGE){
                        //分享图片
                        shareParams.setShareType(Platform.SHARE_IMAGE);
                    }else if(mBuilder.getShareType() == Platform.SHARE_MUSIC){
                        //分享音乐
                        shareParams.setShareType(Platform.SHARE_MUSIC);
                        shareParams.setMusicUrl(mBuilder.getMusicUrl());
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_VIDEO){
                        //分享视频
                        shareParams.setShareType(Platform.SHARE_VIDEO);
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_WEBPAGE){
                        //分享网页
                        shareParams.setShareType(Platform.SHARE_WEBPAGE);
                        shareParams.setUrl(mBuilder.getUrl());
                    }else if(mBuilder.getShareType() == Platform.SHARE_FILE){
                        //分享文件
                        shareParams.setShareType(Platform.SHARE_FILE);
                        shareParams.setFilePath(mBuilder.getfilePath());
                    }
                    break;
                case SINAWEIBO:
                    //新浪微博支持分享文字、本地图片、网络图片和经纬度信息 新浪微博使用客户端分享不会正确回调
                    // 参数说明 text：小于2000字 image：图片最大5M，仅支持JPEG、GIF、PNG格式
                    // latitude：有效范围:-90.0到+90.0，+表示北纬
                    // longitude：有效范围：-180.0到+180.0，+表示东经
                    //如果imagePath和imageUrl同时存在，imageUrl将被忽略。
                    platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                    if (!TextUtils.isEmpty(mBuilder.getUrl())) shareParams.setUrl(mBuilder.getUrl());
                    break;
                case QZONE:
                    //QQ空间支持分享文字和图文 参数说明 title：最多200个字符 text：最多600个字符
                    //QQ空间分享时一定要携带title、titleUrl、site、siteUrl，
                    //QQ空间本身不支持分享本地图片，因此如果想分享本地图片，
                    //图片会先上传到ShareSDK的文件服务器，得到连接以后才分享此链接。
                    //由于本地图片更耗流量，因此imageUrl优先级高于imagePath。
                    //site是分享此内容的网站名称，仅在QQ空间使用；siteUrl是分享此内容的网站地址，仅在QQ空间使用；
                    platform = ShareSDK.getPlatform(QZone.NAME);
                    if (!TextUtils.isEmpty(mBuilder.getTitleUrl())) shareParams.setTitleUrl(mBuilder.getTitleUrl());
                    if (!TextUtils.isEmpty(mBuilder.getSite())) shareParams.setSite(mBuilder.getSite());
                    if (!TextUtils.isEmpty(mBuilder.getSiteUrl())) shareParams.setSiteUrl(mBuilder.getSiteUrl());
                    break;
                case QQ:
                    //QQ分享支持图文和音乐分享 参数说明 title：最多30个字符 text：最多40个字符
                    //QQ分享图文和音乐，在PC版本的QQ上可能只看到一条连接，
                    // 因为PC版本的QQ只会对其白名单的连接作截图，如果不在此名单中，则只是显示连接而已.
                    // 如果只分享图片在PC端看不到图片的，只会显示null，在手机端会显示图片和null字段。
                    platform = ShareSDK.getPlatform(QQ.NAME);
                    if (!TextUtils.isEmpty(mBuilder.getTitleUrl())) shareParams.setTitleUrl(mBuilder.getTitleUrl());
                    if (!TextUtils.isEmpty(mBuilder.getMusicUrl())) shareParams.setMusicUrl(mBuilder.getMusicUrl());
                    break;
                case SHORTMESSAE:
                    //信息分短信和彩信，如果设置了标题或者图片，会直接当作彩信发送。发送信息的时候使用手机的信息软件
                    platform = ShareSDK.getPlatform(ShortMessage.NAME);
                    if (!TextUtils.isEmpty(mBuilder.getAddress())) shareParams.setAddress(mBuilder.getAddress());
                    break;
                case EMAIL:
                    //邮件分享调用手机上的邮件客户端，如果没有客户端，将不能分享邮件
                    platform = ShareSDK.getPlatform(Email.NAME);
                    if (!TextUtils.isEmpty(mBuilder.getAddress())) shareParams.setAddress(mBuilder.getAddress());
                    break;
            }
            platform.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
                    mBuilder.getCallback().onSuccess(mBuilder.getSharePlatform() , "");
                    //lg.i(TAG , "onComplete:" + mBuilder.getSharePlatform());
                }

                @Override
                public void onError(Platform platform, int action, Throwable throwable) {
                    mBuilder.getCallback().onError(mBuilder.getSharePlatform() ,throwable.getMessage());
                    //lg.i(TAG , "onError:" + mBuilder.getSharePlatform() + "," + throwable.getMessage());
                }

                @Override
                public void onCancel(Platform platform, int action) {
                    mBuilder.getCallback().onCancel(mBuilder.getSharePlatform() , "");
                    //lg.i(TAG , "onCancel:" + mBuilder.getSharePlatform() + "," + action);

                }
            });
            platform.share(shareParams);
        }
    }
}
