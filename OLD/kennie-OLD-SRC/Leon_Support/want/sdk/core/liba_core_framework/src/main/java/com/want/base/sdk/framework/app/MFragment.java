package com.want.base.sdk.framework.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.want.ability.AblitilyDelegate;
import com.want.ability.IAbilitable;
import com.want.ability.IAbility;
import com.want.base.sdk.framework.Constants;
import com.want.base.sdk.framework.app.core.BaseUIHelperImpl;
import com.want.base.sdk.framework.app.core.ISDKUIHelper;
import com.want.base.sdk.framework.app.core.UIRunable;
import com.want.base.sdk.framework.app.localbroadcast.ILocalBroadcastHandler;
import com.want.base.sdk.framework.app.localbroadcast.LocalBroadcastHandlerImpl;
import com.want.base.sdk.model.analytic.AnalyticHelper;
import com.want.base.sdk.model.analytic.IAnalytic;

import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * base {@link Fragment}
 * <br>
 */
public class MFragment extends Fragment implements Constants, ILocalBroadcastHandler,
                                                   ISDKUIHelper,
                                                   IAnalytic, IAbilitable {
    private ILocalBroadcastHandler mLocalBroadcastHandler;
    private ISDKUIHelper mSDKUIHelper;
    private IAnalytic mAnalytic;

    private AblitilyDelegate mAbilitable;

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(context);
        mSDKUIHelper = new BaseUIHelperImpl();
        mAnalytic = new AnalyticHelper();
        mAbilitable.onAttach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAbilitable.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAbilitable.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        onEVPageStart(getClass().getName());
        mAbilitable.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        onEVPageEnd(getClass().getName());
        mAbilitable.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterAllLocalReceivers();
        mAbilitable.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAbilitable.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAbilitable.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAbilitable.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mAbilitable.onHiddenChanged(hidden);
    }

    public MFragment() {
        this.mAbilitable = new AblitilyDelegate();
    }

    @Override
    public void addAbility(IAbility ability) {
        mAbilitable.addAbility(ability);
    }

    @Override
    public void removeAbility(IAbility ability) {
        mAbilitable.removeAbility(ability);
    }

    @Override
    public List<IAbility> getAllAbility() {
        return mAbilitable.getAllAbility();
    }

    @Override
    public void clearAbility() {
        mAbilitable.clearAbility();
    }

    /**
     * Framework have a default implements of {@link ILocalBroadcastHandler}, you can set one if you need.
     *
     * @param handler {@Link ILocalBroadcastHandler}
     */
    protected void setLocalBroadcastHandler(ILocalBroadcastHandler handler) {
        this.mLocalBroadcastHandler = handler;
    }

    /**
     * Framework have a default implements of {@link ISDKUIHelper}, you can set one if you need.
     *
     * @param helper {@Link ISDKUIHelper}
     */
    protected void setSDKUIHelper(ISDKUIHelper helper) {
        this.mSDKUIHelper = helper;
    }

    /**
     * Framework have a default implements of {@link IAnalytic}, you can set one if you need.
     *
     * @param analytic {@Link IAnalytic}
     */
    public void setAnalytic(IAnalytic analytic) {
        this.mAnalytic = analytic;
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
     * Unregister all the {@link BroadcastReceiver} register in the {@link LocationManager}
     */
    @Override
    public void unRegisterAllLocalReceivers() {
        mLocalBroadcastHandler.unRegisterAllLocalReceivers();
    }

    /**
     * See {@link LocalBroadcastManager#registerReceiver(BroadcastReceiver, IntentFilter)}
     *
     * @param receiver
     * @param filter
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        mLocalBroadcastHandler.registerLocalReceiver(receiver, filter);
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
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver
     */
    @Override
    public void unRegisterLocalReceiver(BroadcastReceiver receiver) {
        mLocalBroadcastHandler.unRegisterLocalReceiver(receiver);
    }

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
     * Get a single instance handler
     *
     * @return {@link Handler}
     */
    @Override
    public Handler getHandler() {
        return mSDKUIHelper.getHandler();
    }

    /**
     * Run a {@link UIRunable} on the main thread.
     *
     * @param r {@link UIRunable}
     */
    @Override
    public void post(UIRunable r) {
        mSDKUIHelper.post(r);
    }

    /**
     * Run a {@link UIRunable} on the main thread.
     *
     * @param r     {@link UIRunable}
     * @param delay long values to delay
     */
    @Override
    public void postDelay(UIRunable r, long delay) {
        mSDKUIHelper.postDelay(r, delay);
    }

    /**
     * See {@link Resources#getIdentifier(String, String, String)}
     *
     * @param context {@link Context}
     * @param type
     * @param name
     *
     * @return
     */
    @Override
    public int getIdentifier(Context context, String type, String name) {
        return mSDKUIHelper.getIdentifier(context, type, name);
    }

    /**
     * Get the resources id with special name.
     *
     * @param context
     * @param name
     *
     * @return
     */
    @Override
    public int getIdentifier(Context context, String name) {
        return mSDKUIHelper.getIdentifier(context, name);
    }

    /**
     * Get the drawable id with special name
     *
     * @param context
     * @param name
     *
     * @return
     */
    @Override
    public int getDrawableId(Context context, String name) {
        return mSDKUIHelper.getDrawableId(context, name);
    }

    @Override
    public String getString(Context context, int id) {
        return mSDKUIHelper.getString(context, id);
    }

    @Override
    public String getString(Context context, int id, Object... args) {
        return mSDKUIHelper.getString(context, id, args);
    }

    @Override
    public String[] getStringArray(Context context, int id) {
        return mSDKUIHelper.getStringArray(context, id);
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message string
     * @param length  See {@link Toast#LENGTH_LONG}, {@link Toast#LENGTH_SHORT}.
     */
    @Override
    public void toast(Context context, String message, int length) {
        mSDKUIHelper.toast(context, message, length);
    }

    /**
     * Toast a message.
     *
     * @param context context.
     * @param message message string.
     */
    @Override
    public void toast(Context context, String message) {
        mSDKUIHelper.toast(context, message);
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     * @param length  See {@link Toast#LENGTH_LONG}, {@link Toast#LENGTH_SHORT}.
     */
    @Override
    public void toast(Context context, int message, int length) {
        mSDKUIHelper.toast(context, message, length);
    }

    /**
     * Toast a message.
     *
     * @param context context
     * @param message message text id
     */
    @Override
    public void toast(Context context, int message) {
        mSDKUIHelper.toast(context, message);
    }

    @Override
    public void openActivityDurationTrack(boolean track) {
        mAnalytic.openActivityDurationTrack(track);
    }

    @Override
    public void onEVResume(Context context) {
        mAnalytic.onEVResume(context);
    }

    @Override
    public void onEVPause(Context context) {
        mAnalytic.onEVPause(context);
    }

    @Override
    public void onEVPageStart(String label) {
        mAnalytic.onEVPageStart(label);
    }

    @Override
    public void onEVPageEnd(String label) {
        mAnalytic.onEVPageEnd(label);
    }

    @Override
    public void onEVEvent(Context context, String label) {
        mAnalytic.onEVEvent(context, label);
    }

    @Override
    public void onEVEvent(Context context, String label, Map<String, String> maps) {
        mAnalytic.onEVEvent(context, label, maps);
    }

    @Override
    public void onEVEventValue(Context context,
                               String label,
                               Map<String, String> maps,
                               int duration) {
        mAnalytic.onEVEventValue(context, label, maps, duration);
    }
}
