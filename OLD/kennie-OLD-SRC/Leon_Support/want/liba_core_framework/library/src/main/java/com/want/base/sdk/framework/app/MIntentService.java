package com.want.base.sdk.framework.app;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.want.base.sdk.framework.Constants;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/21<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Base {@link IntentService}
 * <br>
 */
@SuppressLint("Registered")
public class MIntentService extends IntentService implements Constants {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MIntentService(String name) {
        super(name);
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
