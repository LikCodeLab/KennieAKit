package com.want.base.sdk.framework.app.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 权限请求Activity。
 * <br>
 */
public class RequestPermissionActivity extends Activity implements PermissionConstants {


    /**
     * 加载权限请求Activity
     *
     * @param context     {@link Context}
     * @param permissions 需要请求的权限数组
     * @param requestCode 请求Id
     * @param extras      附带的额外数据
     */
    public static void launch(Context context, String[] permissions, int requestCode, Bundle extras) {
        Intent intent = new Intent(context, RequestPermissionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        bundle.putBundle(EXTRAS, extras);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private Bundle mExtraBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;

        final Bundle extras = getIntent().getExtras();
        final String[] permissions = extras.getStringArray(PERMISSION);
        final int requestCode = extras.getInt(REQUEST_CODE, 0);
        mExtraBundle = extras.getBundle(EXTRAS);

        // 不再做权限的检查，直接向系统请求权限
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent();
        intent.setAction(PermissionUpdatedReceiver.ACTION);
        // 仅本应用可以接收到该广播
        intent.setPackage(getPackageName());

        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION, permissions);
        bundle.putIntArray(GRANTRESULTS, grantResults);
        bundle.putInt(REQUEST_CODE, requestCode);
        bundle.putBundle(EXTRAS, mExtraBundle);

        intent.putExtras(bundle);

        sendBroadcast(intent);
        finish();
    }
}