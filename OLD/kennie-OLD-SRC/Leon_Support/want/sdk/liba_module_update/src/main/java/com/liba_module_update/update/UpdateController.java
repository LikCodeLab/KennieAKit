package com.liba_module_update.update;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.liba_module_update.download.FileDownloadManager;
import com.liba_module_update.util.SpUtils;

/**
 * <b>Create Date:</b> 16/9/21<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class UpdateController {

    private boolean isStart;

    private Context mContext;

    public UpdateController(Context context, boolean isStart) {
        this.isStart = isStart;
        this.mContext = context;
    }

    public void download(String url, String title) {
        long downloadId = SpUtils.getInstance(mContext).getLong(UpdateVariables.DOWNLOAD_ID, -1L);
        if (downloadId != -1L) {
            FileDownloadManager fdm = FileDownloadManager.getInstance(mContext);
            int status = fdm.getDownloadStatus(downloadId);
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                Uri uri = fdm.getDownloadUri(downloadId);
                if (uri != null) {
                    if (isStart) {
                        startInstall(mContext, uri);
                        return;
                    } else {
                        fdm.getDm().remove(downloadId);
                    }
                }
                start(mContext, url, title);
            } else if (status == DownloadManager.STATUS_FAILED) {
                start(mContext, url, title);
            }
        } else {
            start(mContext, url, title);
        }
    }

    private static void start(Context context, String url, String title) {
        long id = FileDownloadManager.getInstance(context).startDownload(url,
                title, "");
        SpUtils.getInstance(context).putLong(UpdateVariables.DOWNLOAD_ID, id);
    }

    public static void startInstall(Context context, Uri uri) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, UpdateVariables.DOWNLOAD_DATA_TYPE);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }
}
