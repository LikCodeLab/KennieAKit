package com.want.base.sdk.model.update.impl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import com.want.base.sdk.model.update.IUpdateable;
import com.want.base.sdk.model.update.UpdateAgent;
import com.want.core.log.lg;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/11/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Update agent implements of Umeng.
 * <br>
 */
public class UmengAgent implements IUpdateable {

    /**
     * Normal update
     *
     * @param context context
     */
    @Override
    public void update(Context context) {
        UmengUpdateAgent.update(context);
    }

    /**
     * Silent update
     *
     * @param context context
     */
    @Override
    public void silentUpdate(Context context) {
        UmengUpdateAgent.silentUpdate(context);
    }

    /**
     * Force update
     *
     * @param context context
     */
    @Override
    public void forceUpdate(Context context) {
        UmengUpdateAgent.forceUpdate(context);
    }

    /**
     * Install the apk file
     *
     * @param context context
     * @param file    {@link File}
     */
    @Override
    public void install(Context context, File file) {
        UmengUpdateAgent.startInstall(context, file);
    }

    /**
     * Setup agent config
     *
     * @param builder {@link UpdateAgent.Builder}
     */
    @Override
    public void setupConfig(final Context context, UpdateAgent.Builder builder) {
        UmengUpdateAgent.setUpdateOnlyWifi(builder.onlyWithWifi);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int i, final UpdateResponse updateResponse) {
                lg.d("umeng", "status: " + i + ", response: " + updateResponse.updateLog);
                // json example
                // {
                // "status":"1",
                // "force_ver":"10",
                // "delta":"false",
                // "path":"http://www.apk.apk",
                // "size":"102400",
                // "ver_code":"10",
                // "ver_name":"1.0",
                // "proto_ver":"1.0",
                // "update_log":"1. 版本号升级到10; \r\n2. 增加友盟自动更新测试; \r\n3. 仅用于测试"
                // }
                JSONObject result = null;
                try {
                    result = new JSONObject(updateResponse.updateLog);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (null == result) {
                    return;
                }

                final PackageManager pm = context.getPackageManager();
                final int versionCode;
                try {
                    final PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                    versionCode = pi.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                String temp = result.optString("force_ver");
                if (TextUtils.isEmpty(temp)) {
                    return;
                }
                final String[] vers = temp.split(",");

                boolean force = false;
                int version;
                for (String ver : vers) {
                    version = Integer.valueOf(ver);
                    if (force = (versionCode <= version)) {
                        break;
                    }
                }

                UmengUpdateAgent.setUpdateAutoPopup(false);
                updateResponse.updateLog = result.optString("update_log");
                UmengUpdateAgent.showUpdateDialog(context, updateResponse);

                // 强制更新的情况下，禁用自动弹出升级对话框
                if (force) {
                    UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {
                        @Override
                        public void onClick(int i) {
                            switch (i) {
                                case UpdateStatus.Update:
                                    break;
                                default: {
                                    Toast.makeText(context, "您需要更新应用才能继续使用", Toast.LENGTH_SHORT).show();
                                    if (context instanceof Activity) {
                                        ((Activity) context).finish();
                                    }
                                }
                            }
                        }
                    });
                }

            }
        });
    }
}
