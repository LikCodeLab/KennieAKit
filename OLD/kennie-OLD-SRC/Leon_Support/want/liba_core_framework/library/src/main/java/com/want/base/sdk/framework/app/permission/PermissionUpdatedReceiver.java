package com.want.base.sdk.framework.app.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class PermissionUpdatedReceiver extends BroadcastReceiver implements PermissionConstants {


    public abstract void onRequestPermissionsResult(int requestCode,
                                                    String[] permissions,
                                                    int[] grantResults,
                                                    Bundle extras);

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (TextUtils.isEmpty(action)
            || !ACTION.equals(action)) {
            return;
        }
        final Bundle bundle = intent.getExtras();

        final String[] permissions = bundle.getStringArray(PERMISSION);
        final int[] grantResults = bundle.getIntArray(GRANTRESULTS);
        final int requestCode = bundle.getInt(REQUEST_CODE, 0);
        final Bundle extras = bundle.getBundle(EXTRAS);

        onRequestPermissionsResult(requestCode, permissions, grantResults, extras);
    }
}