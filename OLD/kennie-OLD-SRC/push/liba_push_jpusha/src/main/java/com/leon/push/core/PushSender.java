package com.leon.push.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * <b>Project:</b> liba_push_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class PushSender {
    private PushSender() {
        // hide
    }

    /**
     * Send the push message to PushReceiver
     *
     * @param context context
     * @param bundle  bundle
     */
    public static void sendPush(Context context, String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClassName(context, IPushAgent.PUSH_SENDER);
        intent.setAction(action);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }
}
