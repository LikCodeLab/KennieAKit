package com.kennie.http.callbacks;

import com.kennie.http.exception.ApiException;
import com.kennie.http.observer.ApiObserver;
import com.kennie.http.utils.ThreadUtils;

import io.reactivex.rxjava3.annotations.NonNull;
import okhttp3.ResponseBody;


public abstract class UploadCallBack extends ApiObserver<ResponseBody> implements TransmitCallback {

    private final boolean autoBackMainThread;

    public UploadCallBack() {
        this.autoBackMainThread = true;
    }

    public UploadCallBack(boolean autoBackMainThread) {
        this.autoBackMainThread = autoBackMainThread;
    }

    @Override
    protected void onSuccess(@NonNull ResponseBody data) {

    }

    @Override
    protected void onError(ApiException e) {
        if (autoBackMainThread) {
            ThreadUtils.getMainHandler().post(() -> onFailed(e));
        } else {
            onFailed(e);
        }
    }

    @Override
    protected void finished(boolean success) {
        if (autoBackMainThread) {
            ThreadUtils.getMainHandler().postDelayed(() -> done(success)
                    , 500);
        } else {
            done(success);
        }
    }

}
