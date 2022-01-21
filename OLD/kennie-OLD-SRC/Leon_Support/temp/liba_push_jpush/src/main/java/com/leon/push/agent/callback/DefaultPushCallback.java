package com.leon.push.agent.callback;

import android.content.Context;
import android.os.Bundle;

import com.leon.push.core.IPushCallback;


/**
 * <b>Project:</b> liba_push_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Push callback default implements.
 * <br>
 */
public class DefaultPushCallback implements IPushCallback {


    /**
     * Called after device registration.
     *
     * @param context context
     * @param id      registration id.
     */
    @Override
    public void onRegistration(Context context, String id) {
        // TODO: 15/10/30
    }

    /**
     * Called after device unregistration.
     *
     * @param context context
     * @param id      registration id
     */
    @Override
    public void onUnRegistration(Context context, String id) {
        // TODO: 15/10/30
    }

    /**
     * Called after custom message received.
     *
     * @param context context
     * @param data    json formated data
     */
    @Override
    public void onMessage(Context context, String data) {
        // TODO: 15/10/30
    }

    /**
     * Called after rich message received.
     *
     * @param context context
     * @param data    json formated data
     */
    @Override
    public void onRichMessage(Context context, String data) {
        // TODO: 15/10/30
    }

    /**
     * Called after notification received.
     *
     * @param context context
     * @param id      notification id
     */
    @Override
    public void onNotificationReceived(Context context, String id) {
        // TODO: 15/10/30
    }

    /**
     * Called after notification opened
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    @Override
    public void onNotificationOpened(Context context, Bundle data) {
        // TODO: 15/10/30
    }

    /**
     * Called after data not be handled.
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    @Override
    public void onUnHandledMessage(Context context, Bundle data) {
        // TODO: 15/12/1
    }
}
