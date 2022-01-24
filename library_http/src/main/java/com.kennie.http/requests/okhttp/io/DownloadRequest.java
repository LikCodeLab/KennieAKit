package com.kennie.http.requests.okhttp.io;

import android.util.Log;
import com.kennie.http.RequestManager;
import com.kennie.http.RxPanda;
import com.kennie.http.callbacks.DownloadCallBack;
import com.kennie.http.callbacks.TransmitCallback;
import com.kennie.http.interceptor.DownloadInterceptor;
import com.kennie.http.transformer.RxScheduler;

import java.io.File;
import java.io.IOException;



public class DownloadRequest extends IORequest<DownloadRequest> {

    private File targetFile;


    public DownloadRequest(String url) {
        super(url);
    }

    public DownloadRequest target(String dirName, String fileName) {
        File file = new File(dirName);
        if (!file.exists()) {
            boolean result = file.mkdirs();
            if (!result) {
                Log.e("DownloadRequest :", "mkdirs failed !");
            }
        }
        targetFile = new File(dirName + File.separator + fileName);
        if (!targetFile.exists()) {
            try {
                boolean res = targetFile.createNewFile();
                if (!res) {
                    Log.e("DownloadRequest :", "createNewFile failed !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public DownloadRequest target(File file) {
        targetFile = file;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends TransmitCallback> void execute(T t) {
        DownloadCallBack callback;
        if (t instanceof DownloadCallBack) {
            callback = (DownloadCallBack) t;
            callback.setTargetFile(targetFile);
        } else {
            return;
        }
        if (super.tag != null) {
            RequestManager.get().addTag(super.tag, callback);
        }
        mApi.downFile(url, localParams)
                .doOnSubscribe(disposable -> {
                    if (tag != null) {
                        RxPanda.manager().addTag(tag, disposable);
                    }
                })
                .compose(RxScheduler.io())
                .subscribe(callback);
    }

    public void request(DownloadCallBack callback) {
        if (targetFile == null || !targetFile.exists()) {
            Log.d("DownloadRequest", "targeFile not found !!!");
        }
        netInterceptor(new DownloadInterceptor(callback));
        injectLocalParams();
        execute(callback);
    }
}
