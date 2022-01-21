package com.liba_module_update.download;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.liba_module_update.net.CodeInfo;
import com.liba_module_update.update.UpdateVariables;
import com.liba_module_update.util.SpUtils;
import com.liba_module_update.util.VersionCode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <b>Create Date:</b> 16/9/22<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class DownloadTask {

    private CodeInfo mCodeInfo;

    private Context mContext;

    private ProgressDialog mPDialog;

    private static DownloadTask downloadTask;

    private DownloadTask(CodeInfo mCodeInfo, Context mContext) {
        this.mCodeInfo = mCodeInfo;
        this.mContext = mContext;
    }

    public static synchronized DownloadTask getInstance(CodeInfo mCodeInfo, Context mContext) {
        if (downloadTask == null) {
            downloadTask = new DownloadTask(mCodeInfo, mContext);
        }
        return downloadTask;
    }

    public void start() {
        final boolean is = mCodeInfo.upgrade == 800;
        final ProgressDialog progressDialog = getDialog(is);
        progressDialog.show();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                downLoadAPK(mCodeInfo.url, progressDialog, is);
            }
        };
        new Thread(runnable).start();
    }

    private void downLoadAPK(String path, ProgressDialog p, final boolean isForce) {
        boolean have = SpUtils.getInstance(mContext).getBoolean(UpdateVariables.DOWNLOAD_HAVE, false);
        if (!have) {
            //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                URL url;
                HttpURLConnection conn;
                try {
                    url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(30000);
                    InputStream is = conn.getInputStream();
                    String name = VersionCode.getVersionName(mContext);
                    String apkPath = Environment.getExternalStorageDirectory() + name + UpdateVariables.DOWNLOAD_APP_NAME;
                    File file = new File(apkPath);
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    int total = 0;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        total += len;
                        //下载进度
                        if (isForce) {
                            p.setProgress(total);
                        }
                    }
                    fos.close();
                    bis.close();
                    is.close();
                    installApk(file);
                    SpUtils.getInstance(mContext).putBoolean(UpdateVariables.DOWNLOAD_HAVE, true);
                    SpUtils.getInstance(mContext).putObject(UpdateVariables.DOWNLOAD_APK, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            //apk文件已存在 直接安装
            File apk = (File) SpUtils.getInstance(mContext).getObject(UpdateVariables.DOWNLOAD_APK);
            installApk(apk);
        }
    }

    private ProgressDialog getDialog(boolean is) {
        mPDialog = new ProgressDialog(mContext);
        mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mPDialog.setTitle("正在下载更新");
//        mPDialog.setMessage("正在下载更新");
        mPDialog.setMax(mCodeInfo.size);
        //设置进度条是否可以按退回键取消
        mPDialog.setCancelable(is);
//        设置点击进度对话框外的区域对话框不消失
        mPDialog.setCanceledOnTouchOutside(is);
        return mPDialog;
    }

    private void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), UpdateVariables.DOWNLOAD_DATA_TYPE);
        mContext.startActivity(intent);
    }
}
