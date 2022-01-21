//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛

package com.jackylee.push.jpush.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.List;

/**
 * All rights Reserved, Designed By www.likosoft.com
 *
 * @TITLE: ILocalBroadcastHandler
 * @PACKAGE com.liko.support.base.localbroadcast
 * @DESCRIPTION: Local broadcast's interface define.
 * @AUTHOR: Liko
 * @DATE: 2017/01/04 20:36
 * @VERSION V0.1
 * @COPYRIGHT: 2017 www.likosoft.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的。
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
