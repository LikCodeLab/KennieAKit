package com.liba_module_update.download;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.liba_module_update.util.VersionCode;

/**
 * <b>Create Date:</b> 16/9/21<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class FileDownloadManager {

    private DownloadManager dm;
    private Context context;
    private static FileDownloadManager instance;

    private FileDownloadManager(Context context) {
        dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        this.context = context.getApplicationContext();
    }

    public static FileDownloadManager getInstance(Context context) {
        if (instance == null) {
            instance = new FileDownloadManager(context);
        }
        return instance;
    }


    /**
     * 下载并保存apk
     */
    public long startDownload(String uri, String title, String description) {
        String name = VersionCode.getVersionName(context);
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(uri));

        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //req.setAllowedOverRoaming(false);

        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).toString();

        //保存路径
        //file:///storage/emulated/0/Android/data/your-package/files/Download/update.apk
        req.setDestinationInExternalFilesDir(context, path, name);
        //req.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, name + UpdateVariables.DOWNLOAD_APP_NAME);


        //file:///storage/emulated/0/Download/update.apk
        //req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name+ UpdateVariables.DOWNLOAD_APP_NAME);


        // 设置菜单栏显示信息
        req.setTitle(title);
        req.setDescription(description);

        return dm.enqueue(req);
    }


    /**
     * 获取文件保存的路径
     */
    public String getDownloadPath(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = dm.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                }
            } finally {
                c.close();
            }
        }
        return null;
    }


    /**
     * 获取保存文件的地址
     */
    public Uri getDownloadUri(long downloadId) {
        Log.d("DownLoadService:", "path===" + dm.getUriForDownloadedFile(downloadId));
        return dm.getUriForDownloadedFile(downloadId);
    }

    public DownloadManager getDm() {
        return dm;
    }


    /**
     * 获取下载状态
     */
    public int getDownloadStatus(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = dm.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {

                    return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

                }
            } finally {
                c.close();
            }
        }
        return -1;
    }
}
