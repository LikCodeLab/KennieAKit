package com.want.base.sdk.model.crash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.want.base.sdk.framework.app.MFragmentActivity;
import com.want.base.sdk.framework.app.dialog.AlertDialogFragment;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * After app crashed, show a dialog.
 * <br>
 */
public class CrashDialogActivity extends MFragmentActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, CrashDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setTitle("提示！");
        alertDialogFragment.setMessage("程序出现了异常！");
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };

        alertDialogFragment.setCancelable(false);
        alertDialogFragment.setPositiveButton("提交", listener);
        alertDialogFragment.setNegativeButton("取消", listener);
        alertDialogFragment.show(getSupportFragmentManager());

    }
}
