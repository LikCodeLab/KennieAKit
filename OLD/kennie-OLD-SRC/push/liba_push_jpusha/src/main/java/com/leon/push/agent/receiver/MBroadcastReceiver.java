package com.leon.push.agent.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;

import com.leon.push.agent.localbroadcast.ILocalBroadcastHandler;
import com.leon.push.agent.localbroadcast.LocalBroadcastHandlerImpl;

import java.util.List;


/**
 * <b>Project:</b> liba_push_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Base {@link BroadcastReceiver}
 * <br>
 */
public abstract class MBroadcastReceiver extends BroadcastReceiver implements
        ILocalBroadcastHandler {
    private ILocalBroadcastHandler mLocalBroadcastHandler;

    public MBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(context);
    }

    /**
     * Get all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
     *
     * @return {@link BroadcastReceiver}'s list.
     */
    @Override
    public List<BroadcastReceiver> getRegisteredReceivers() {
        return mLocalBroadcastHandler.getRegisteredReceivers();
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param actions  string actions
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, String... actions) {
        mLocalBroadcastHandler.registerLocalReceiver(receiver, actions);
    }

    /**
     * See {@link LocalBroadcastManager#registerReceiver(BroadcastReceiver, IntentFilter)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param filter   {@link IntentFilter}
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        mLocalBroadcastHandler.registerLocalReceiver(receiver, filter);
    }

    /**
     * See {@link LocalBroadcastManager#sendBroadcast(Intent)}
     *
     * @param intent
     * @return
     */
    @Override
    public boolean sendLocalBroadcast(Intent intent) {
        return mLocalBroadcastHandler.sendLocalBroadcast(intent);
    }

    /**
     * See {@link LocalBroadcastManager#sendBroadcastSync(Intent)}
     *
     * @param intent
     */
    @Override
    public void sendLocalBroadcastSync(Intent intent) {
        mLocalBroadcastHandler.sendLocalBroadcastSync(intent);
    }

    /**
     * Unregister all the {@link BroadcastReceiver} register in the {@link LocationManager}
     */
    @Override
    public void unRegisterAllLocalReceivers() {
        mLocalBroadcastHandler.unRegisterAllLocalReceivers();
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver
     */
    @Override
    public void unRegisterLocalReceiver(BroadcastReceiver receiver) {
        mLocalBroadcastHandler.unRegisterLocalReceiver(receiver);
    }
}
