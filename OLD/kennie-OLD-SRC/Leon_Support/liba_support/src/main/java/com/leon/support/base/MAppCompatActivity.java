package com.leon.support.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.log.lg;
import com.leon.support.R;
import com.leon.support.base.model.ability.core.AbilityDelegate;
import com.leon.support.base.model.ability.core.IAbility;
import com.leon.support.base.model.ability.core.IAbilityAble;
import com.leon.support.base.model.analytic.AnalyticHelper;
import com.leon.support.base.model.analytic.IAnalytic;
import com.leon.support.base.framework.core.BaseUIHelperImpl;
import com.leon.support.base.framework.core.ISDKUIHelper;
import com.leon.support.base.framework.core.UIRunable;
import com.leon.support.base.framework.localbroadcast.ILocalBroadcastHandler;
import com.leon.support.base.framework.localbroadcast.LocalBroadcastHandlerImpl;
import com.leon.support.base.framework.toolbar.IToolbarHandler;
import com.leon.support.base.framework.toolbar.ToolbarHandlerImpl;

import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * base {@link AppCompatActivity}
 * <br>
 */
public class MAppCompatActivity extends AppCompatActivity implements MConstants,
        IAbilityAble, IToolbarHandler, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic {


    private AbilityDelegate mAbilityDelegate;
    private IToolbarHandler mToolbarHandler;
    private ILocalBroadcastHandler mLocalBroadcastHandler;
    private ISDKUIHelper mSDKUIHelper;
    private IAnalytic mAnalytic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAbilityDelegate.onCreate(savedInstanceState);
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(this);
        mSDKUIHelper = new BaseUIHelperImpl();
        mToolbarHandler = new ToolbarHandlerImpl();
        mAnalytic = onCreateAnalytic();
        if (null != mAnalytic) {
            mAnalytic.openActivityDurationTrack(false);
        }
        mAbilityDelegate.onCreate(savedInstanceState);
    }

    protected IAnalytic onCreateAnalytic() {
        return new AnalyticHelper();
    }


    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        mAbilityDelegate.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onEVResume(this);
        mAbilityDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onEVPause(this);
        mAbilityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAbilityDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        unRegisterAllLocalReceivers();
        mAbilityDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mAbilityDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAbilityDelegate.onAttach();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAbilityDelegate.onDetach();
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

    public MAppCompatActivity() {
        this.mAbilityDelegate = new AbilityDelegate();
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
     * Framework have a default implements of {@link IToolbarHandler}, you can set one if you need.
     *
     * @param handler {@Link IToolbarHandler}
     */
    public void setToolbarHandler(IToolbarHandler handler) {
        this.mToolbarHandler = handler;
    }

    /**
     * Framework have a default implements of {@link IAnalytic}, you can set one if you need.
     *
     * @param analytic {@Link IAnalytic}
     */
    public void setAnalytic(IAnalytic analytic) {
        this.mAnalytic = analytic;
    }


    @Override
    public void addAbility(IAbility ability) {
        mAbilityDelegate.addAbility(ability);
    }

    @Override
    public void removeAbility(IAbility ability) {
        mAbilityDelegate.removeAbility(ability);
    }

    @Override
    public List<IAbility> getAllAbility() {
        return mAbilityDelegate.getAllAbility();
    }

    @Override
    public void clearAbility() {
        mAbilityDelegate.clearAbility();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar(this, (Toolbar) findViewById(R.id.base_toolbar));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupToolbar(this, (Toolbar) findViewById(R.id.base_toolbar));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setupToolbar(this, (Toolbar) findViewById(R.id.base_toolbar));
    }

    @Override
    public void setupToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        if (null != toolbar) {
            mToolbarHandler.setupToolbar(appCompatActivity, toolbar);
        } else {
            lg.w("lib_support", "Forget to setup your custom toolbar with id 'toolbar' ???");
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
     * Get all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
     *
     * @return {@link BroadcastReceiver}'s list.
     */
    @Override
    public List<BroadcastReceiver> getRegisteredReceivers() {
        return mLocalBroadcastHandler.getRegisteredReceivers();
    }

    /**
     * Unregister all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
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


    @Override
    public void openActivityDurationTrack(boolean track) {
        if (null != mAnalytic) mAnalytic.openActivityDurationTrack(track);
    }

    @Override
    public void onEVResume(Context context) {
        if (null != mAnalytic) mAnalytic.onEVResume(context);
    }

    @Override
    public void onEVPause(Context context) {
        if (null != mAnalytic) mAnalytic.onEVPause(context);
    }

    @Override
    public void onEVPageStart(String label) {
        if (null != mAnalytic) mAnalytic.onEVPageStart(label);
    }

    @Override
    public void onEVPageEnd(String label) {
        if (null != mAnalytic) mAnalytic.onEVPageEnd(label);
    }

    @Override
    public void onEVEvent(Context context, String label) {
        if (null != mAnalytic) mAnalytic.onEVEvent(context, label);
    }

    @Override
    public void onEVEvent(Context context, String label, Map<String, String> maps) {
        if (null != mAnalytic) mAnalytic.onEVEvent(context, label, maps);
    }

    @Override
    public void onEVEventValue(Context context,
                               String label,
                               Map<String, String> maps,
                               int duration) {
        if (null != mAnalytic) mAnalytic.onEVEventValue(context, label, maps, duration);
    }
}
