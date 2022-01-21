package com.learkoo.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import java.util.List;

/**
 * <b>Project:</b> LearKooSDK<br>
 * <b>Create Date:</b> 16/2/26<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 * 应用市场工具类
 * <br>
 */
public class MarketsUtil {

    private static String TAG = MarketsUtil.class.getSimpleName();

    public static void openGooglePlay(Context context) {
        Intent intent = new Intent("com.google.android.finsky.VIEW_MY_DOWNLOADS");
        intent.setComponent(new ComponentName("com.android.vending", "com.android.vending.AssetBrowserActivity"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openAppMarketsAtMyDetails(Activity activity, boolean forResult, int requestCode) {
        String uri = "market://details?id=" + activity.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        //PackageManager.MATCH_DEFAULT_ONLY用于非Launcher触发的启动但是可以接受data的Intent.CATEGORY_DEFAULT
        List<ResolveInfo> infos = activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        //如果没有安装任何应用市场，则startActivity()会报错
        if (infos != null && infos.size() > 0) {
            try {
                if (forResult) {
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	//加这句的话，App还没启动，就会收到onActivityResult()
                    activity.startActivityForResult(intent, requestCode);
                } else {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            } catch (Exception e) {
                Log.e(TAG , e.toString());
            }
        }
    }

    public static void openAppMarkets(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory("android.intent.category.APP_MARKET");	//Intent.CATEGORY_APP_MARKET
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
