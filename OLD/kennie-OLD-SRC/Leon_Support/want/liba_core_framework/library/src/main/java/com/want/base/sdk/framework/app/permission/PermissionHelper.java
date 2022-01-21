package com.want.base.sdk.framework.app.permission;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 权限请求工具类
 * <br>
 */
public class PermissionHelper {

    private PermissionHelper() {
        // hide
    }

    /**
     * 检查是否拥有指定的权限
     *
     * @param context    {@link Context}
     * @param permission 待检查的权限
     *
     * @return true，拥有。
     */
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
               == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 请求权限
     *
     * @param context     {@link Context}
     * @param permissions 需要请求的权限数组
     * @param requestCode 请求Id
     * @param extras      附带的额外数据
     */
    public static void requestPermissions(Context context,
                                          String[] permissions,
                                          int requestCode,
                                          Bundle extras) {
        RequestPermissionActivity.launch(context, permissions, requestCode, extras);
    }

    public static void registerPermissionUpdatedReceiver(Context context,
                                                         PermissionUpdatedReceiver receiver) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(PermissionConstants.ACTION);

        context.registerReceiver(receiver, filter);
    }

    public static void unRegisterPermissionUpdatedReceiver(Context context,
                                                           PermissionUpdatedReceiver receiver) {
        context.unregisterReceiver(receiver);
    }
}