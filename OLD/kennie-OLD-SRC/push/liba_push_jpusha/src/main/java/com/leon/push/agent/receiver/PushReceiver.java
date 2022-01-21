package com.leon.push.agent.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.leon.log.lg;
import com.leon.push.agent.PushAgent;
import com.leon.push.core.IPushAgent;
import com.leon.push.core.IPushCallback;
import com.leon.push.core.LogHelper;


/**
 * <b>Project:</b> liba_push_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Push message receiver.
 * <br>
 */
public class PushReceiver extends MBroadcastReceiver {
    public static final String ACTION = "com.learkoo.push.ACTION_PUSH_RECEIVED";

    private static String formateMessage(String message) {
        return String.format("[push] [pushreceiver] %s", message);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        final Bundle extras = intent.getExtras();
        final String action = intent.getAction();

        if (LogHelper.DEBUG) {
            lg.v(formateMessage("onReceive, action: " + action));
        }

        final IPushCallback callback = PushAgent.getInstance().getPushCallback();
        if (null == callback) {
            lg.w(formateMessage("onReceive, push callback is null !!"));
            return;
        }

        // device registrted
        if (IPushAgent.ACTION_REGISTRATION.equals(action)) {
            callback.onRegistration(context, extras.getString(IPushAgent.PUSH_DATA));
            // custom message received
        } else if (IPushAgent.ACTION_MESSAGE.equals(action)) {
            callback.onMessage(context, extras.getString(IPushAgent.PUSH_DATA));
            // rich message received
        } else if (IPushAgent.ACTION_RICH_MESSAGE.equals(action)) {
            callback.onMessage(context, extras.getString(IPushAgent.PUSH_DATA));
            // notification received
        } else if (IPushAgent.ACTION_NOTIFICATION_RECEIVED.equals(action)) {
            callback.onNotificationReceived(context, extras.getString(IPushAgent.PUSH_DATA));
            // notification opened
        } else if (IPushAgent.ACTION_NOTIFICATION_OPENED.equals(action)) {
            callback.onNotificationOpened(context, extras);
            // default
        } else {
            callback.onUnHandledMessage(context, extras);
        }
    }

}
