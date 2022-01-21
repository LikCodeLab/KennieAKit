package com.jackylee.push.jpush.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.LinkedList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.likosoft.com
 *
 * @TITLE: LocalBroadcastHandlerImpl
 * @PACKAGE com.liko.support.base.localbroadcast
 * @DESCRIPTION: Local broadcast implements.
 * @AUTHOR: Liko
 * @DATE: 2017/01/04 20:37
 * @VERSION V0.1
 * @COPYRIGHT: 2017 www.likosoft.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的。
 */
public class LocalBroadcastHandlerImpl implements ILocalBroadcastHandler {

    private Context mContext;

    private LinkedList<BroadcastReceiver> mReceivers;

    public LocalBroadcastHandlerImpl(Context context) {
        this.mContext = context;
        this.mReceivers = new LinkedList<>();
    }

    private LocalBroadcastManager getBroadcastManager(Context context) {
        return LocalBroadcastManager.getInstance(context);
    }

    /**
     * Get all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
     *
     * @return {@link BroadcastReceiver}'s list.
     */
    @Override
    public List<BroadcastReceiver> getRegisteredReceivers() {
        return mReceivers;
    }

    /**
     * Unregister all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
     */
    @Override
    public synchronized void unRegisterAllLocalReceivers() {
        for (BroadcastReceiver receiver : mReceivers) {
            getBroadcastManager(mContext).unregisterReceiver(receiver);
        }
        mReceivers.clear();
    }

    /**
     * See {@link LocalBroadcastManager#registerReceiver(BroadcastReceiver, IntentFilter)}
     *
     * @param receiver
     * @param filter
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        getBroadcastManager(mContext).registerReceiver(receiver, filter);
        mReceivers.add(receiver);
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param actions  string actions
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, String... actions) {
        IntentFilter filter = new IntentFilter();
        if (null != actions && 0 != actions.length) {
            for (String action : actions) {
                filter.addAction(action);
            }
        }
        getBroadcastManager(mContext).registerReceiver(receiver, filter);
        mReceivers.add(receiver);
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver
     */
    @Override
    public void unRegisterLocalReceiver(BroadcastReceiver receiver) {
        getBroadcastManager(mContext).unregisterReceiver(receiver);
        mReceivers.remove(receiver);
    }

    /**
     * See {@link LocalBroadcastManager#sendBroadcast(Intent)}
     *
     * @param intent
     * @return
     */
    @Override
    public boolean sendLocalBroadcast(Intent intent) {
        return getBroadcastManager(mContext).sendBroadcast(intent);
    }

    /**
     * See {@link LocalBroadcastManager#sendBroadcastSync(Intent)}
     *
     * @param intent
     */
    @Override
    public void sendLocalBroadcastSync(Intent intent) {
        getBroadcastManager(mContext).sendBroadcastSync(intent);
    }
}
