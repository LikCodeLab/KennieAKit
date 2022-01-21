package com.leon.utils.temp.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import java.io.File;


public class IntentUtil {
    private static IntentUtil instance = null;

    /**
     * 返回该类的一个实例
     */
    public static IntentUtil getInstance() {
        if (instance == null) {
            synchronized (IntentUtil.class) {
                if (instance == null)
                    instance = new IntentUtil();
            }
        }
        return instance;
    }

//	/**
//	 * 安装APK
//	 * @param context
//	 * @param file APK文件对象
//	 */
//	public void installApk( Context ctx, File file )
//	{
//		Intent intent = new Intent();
//
//		intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
//		intent.setAction( android.content.Intent.ACTION_VIEW );
//
//		/* 调用getMIMEType()来取得MimeType */
//		String type = MimeTypeUtils.getMIMEType( file );
//
//		/* 设置intent的file与MimeType */
//		intent.setDataAndType( Uri.fromFile( file ), type );
//
//		ctx.startActivity( intent );
//	}

    /**
     * 判断是否已经安装某个应用
     *
     * @param packageName 应用程序的包名
     * @return
     */
    public boolean existPackage(Context ctx, String packageName) {
        try {
            PackageManager pm = ctx.getPackageManager();
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 启动某个应用程序
     *
     * @param ctx
     * @param packageName 应用程序的包名
     */
    public void startPackage(Context ctx, String packageName) {
        Intent intent = ctx.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            ctx.startActivity(intent);
        }
    }

    /**
     * 调用系统的分享程序
     *
     * @param ctx
     * @param tip 分享内容
     */
    public void share(Context ctx, String tip) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
//		intent.putExtra( Intent.EXTRA_SUBJECT, "分享" ); //将以彩信形式
        intent.putExtra(Intent.EXTRA_TEXT, tip);
        ctx.startActivity(Intent.createChooser(intent, "分享"));
    }


    /**
     * 打开系统的浏览器浏览网页
     *
     * @param ctx
     * @param url
     */
    public void startSystemBrowser(Context ctx, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent it = new Intent();
            it.setAction("android.intent.action.VIEW");
            it.setData(uri);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setClassName("com.android.browser",
                    "com.android.browser.BrowserActivity");
            ctx.startActivity(it);
        } catch (Exception e) {
            startBrowser(url, ctx);
        }

    }

    /**
     * 打开浏览器
     *
     * @param url
     * @param ctx
     */
    public void startBrowser(String url, Context ctx) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(it);
    }

    /**
     * 启动某个Activity
     *
     * @param ctx
     * @param cls
     */
    public void startActivity(Context ctx, Class cls) {
        /* new一个Intent对象，并指定class */
        Intent intent = new Intent();
        intent.setClass(ctx, cls);
		/* 调用Activity */
        ctx.startActivity(intent);
    }

    /**
     * 跳转到系统发短信界面
     */
    public void gotoSystemSendMessage(Context ctx, String number, String body) {
        Intent intent = null;
        if (StringUtil.isEmpty(number))
            intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
        else
            intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (!StringUtil.isEmpty(body))
            intent.putExtra("sms_body", body);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    /**
     * 跳转到系统发彩信界面
     */
    public void gotoSendMMS(Context ctx, String number) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mmsto", number, null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }


    /**
     * 回收该类的数据资源
     */
    public static void recycle() {
        instance = null;
    }


    /**
     * 获取安装App(支持6.0)的意图
     *
     * @param filePath 文件路径
     * @return intent
     */
    public static Intent getInstallAppIntent(String filePath) {
        return getInstallAppIntent(FileUtil.getFileByPath(filePath));
    }

    /**
     * 获取安装App(支持6.0)的意图
     *
     * @param file 文件
     * @return intent
     */
    public static Intent getInstallAppIntent(File file) {
        if (file == null) return null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type;
        if (Build.VERSION.SDK_INT < 23) {
            type = "application/vnd.android.package-archive";
        } else {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileUtil.getFileExtension(file));
        }
        intent.setDataAndType(Uri.fromFile(file), type);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取卸载App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     *
     * @param context     上下文
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取App具体设置的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享文本的意图
     *
     * @param content 分享文本
     * @return intent
     */
    public static Intent getShareTextIntent(String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享图片的意图
     *
     * @param content   文本
     * @param imagePath 图片文件路径
     * @return intent
     */
    public static Intent getShareImageIntent(String content, String imagePath) {
        return getShareImageIntent(content, FileUtil.getFileByPath(imagePath));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 文本
     * @param image   图片文件
     * @return intent
     */
    public static Intent getShareImageIntent(String content, File image) {
        if (!FileUtil.isFileExists(image)) return null;
        return getShareImageIntent(content, Uri.fromFile(image));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 分享文本
     * @param uri     图片uri
     * @return intent
     */
    public static Intent getShareImageIntent(String content, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className) {
        return getComponentIntent(packageName, className, null);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取拍照的意图
     *
     * @param outUri 输出的uri
     * @return 拍照的意图
     */
    public static Intent getCaptureIntent(Uri outUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        return intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取选择照片的Intent
     *
     * @return
     */
    public static Intent getPickIntentWithGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        return intent.setType("image/*");
    }

    /**
     * 获取从文件中选择照片的Intent
     *
     * @return
     */
    public static Intent getPickIntentWithDocuments() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        return intent.setType("image/*");
    }


    public static Intent buildImageGetIntent(Uri saveTo, int outputX, int outputY, boolean returnData) {
        return buildImageGetIntent(saveTo, 1, 1, outputX, outputY, returnData);
    }

    public static Intent buildImageGetIntent(Uri saveTo, int aspectX, int aspectY,
                                             int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        intent.putExtra("output", saveTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int outputX, int outputY, boolean returnData) {
        return buildImageCropIntent(uriFrom, uriTo, 1, 1, outputX, outputY, returnData);
    }

    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int aspectX, int aspectY,
                                              int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uriFrom, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uriTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }
}
