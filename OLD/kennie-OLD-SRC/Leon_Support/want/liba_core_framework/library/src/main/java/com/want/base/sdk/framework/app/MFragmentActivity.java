package com.want.base.sdk.framework.app;

import android.annotation.SuppressLint;
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
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.want.ability.AblitilyDelegate;
import com.want.ability.IAbilitable;
import com.want.ability.IAbility;
import com.want.base.sdk.R;
import com.want.base.sdk.framework.Constants;
import com.want.base.sdk.framework.app.core.BaseUIHelperImpl;
import com.want.base.sdk.framework.app.core.ISDKUIHelper;
import com.want.base.sdk.framework.app.core.UIRunable;
import com.want.base.sdk.framework.app.localbroadcast.ILocalBroadcastHandler;
import com.want.base.sdk.framework.app.localbroadcast.LocalBroadcastHandlerImpl;
import com.want.base.sdk.framework.app.toolbar.IToolbarHandler;
import com.want.base.sdk.framework.app.toolbar.ToolbarHandlerImpl;
import com.want.base.sdk.model.analytic.AnalyticHelper;
import com.want.base.sdk.model.analytic.IAnalytic;
import com.want.core.log.lg;

import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * base {@link FragmentActivity}
 * <br>
 */
@SuppressLint("Registered")
public class MFragmentActivity extends AppCompatActivity implements Constants,
                                                                    ILocalBroadcastHandler,
                                                                    ISDKUIHelper,
                                                                    IAnalytic,
                                                                    IToolbarHandler, IAbilitable {

    private ILocalBroadcastHandler mLocalBroadcastHandler;
    private ISDKUIHelper mSDKUIHelper;
    private IAnalytic mAnalytic;
    private IToolbarHandler mToolbarHandler;
    private AblitilyDelegate mAbilitable;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(this);
        mSDKUIHelper = new BaseUIHelperImpl();

        mAnalytic = new AnalyticHelper();
        mAnalytic.openActivityDurationTrack(false);

        mToolbarHandler = new ToolbarHandlerImpl();
        mAbilitable.onCreate(savedInstanceState);

    }

    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        mAbilitable.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onEVResume(this);
        mAbilitable.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onEVPause(this);
        mAbilitable.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAbilitable.onStop();
    }

    @Override
    protected void onDestroy() {
        unRegisterAllLocalReceivers();
        mAbilitable.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mAbilitable.onPostCreate(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAbilitable.onAttach();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAbilitable.onDetach();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final FragmentManager fm = getSupportFragmentManager();
        final List<Fragment> fragments = fm.getFragments();
        if (null != fragments) {
            for (Fragment f : fragments) {
                if (null != f) {
                    f.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    public MFragmentActivity() {
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
     * Framework have a default implements of {@link IToolbarHandler}, you can set one if you need.
     *
     * @param handler {@Link IToolbarHandler}
     */
    public void setToolbarHandler(IToolbarHandler handler) {
        this.mToolbarHandler = handler;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar(this, (Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupToolbar(this, (Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setupToolbar(this, (Toolbar) findViewById(R.id.toolbar));
    }

    /**
     * Setup and config toolbar.
     *
     * @param appCompatActivity {@link AppCompatActivity}
     * @param toolbar           {@link Toolbar}
     */
    @Override
    public void setupToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        this.mToolbar = toolbar;
        if (null != mToolbar) {
            mToolbarHandler.setupToolbar(appCompatActivity, toolbar);
        } else {
            lg.w("sdk", "Forget to setup your custom toolbar with id 'toolbar' ???");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToolbarHandler.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
     * Run a {@link Runnable} on the main thread.
     *
     * @param r {@link Runnable}
     */
    @Override
    public void post(UIRunable r) {
        mSDKUIHelper.post(r);
    }

    /**
     * Run a {@link Runnable} on the main thread.
     *
     * @param r     {@link Runnable}
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
     * @param message message text id
     */
    @Override
    public void toast(Context context, int message) {
        mSDKUIHelper.toast(context, message);
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
