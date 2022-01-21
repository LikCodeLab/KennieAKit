package com.leon.push.agent.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;


/**
 * <b>Project:</b> liba_push_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Local broadcast's interface define.
 * <br>
 */
public interface ILocalBroadcastHandler {

    /**
     * Get all the {@link BroadcastReceiver} register in the {@link android.support.v4.content.LocalBroadcastManager}
     *
     * @return {@link BroadcastReceiver}'s list.
     */
    List<BroadcastReceiver> getRegisteredReceivers();

    /**
     * Unregister all the {@link BroadcastReceiver} register in the {@link android.location.LocationManager}
     */
    void unRegisterAllLocalReceivers();

    /**
     * See {@link android.support.v4.content.LocalBroadcastManager#registerReceiver(BroadcastReceiver, IntentFilter)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param filter   {@link IntentFilter}
     */
    void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter);

    /**
     * See {@link android.support.v4.content.LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param actions  string actions
     */
    void registerLocalReceiver(BroadcastReceiver receiver, String... actions);

    /**
     * See {@link android.support.v4.content.LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver
     */
    void unRegisterLocalReceiver(BroadcastReceiver receiver);

    /**
     * See {@link android.support.v4.content.LocalBroadcastManager#sendBroadcast(Intent)}
     *
     * @param intent
     * @return
     */
    boolean sendLocalBroadcast(Intent intent);

    /**
     * See {@link android.support.v4.content.LocalBroadcastManager#sendBroadcastSync(Intent)}
     *
     * @param intent
     */
    void sendLocalBroadcastSync(Intent intent);
}
