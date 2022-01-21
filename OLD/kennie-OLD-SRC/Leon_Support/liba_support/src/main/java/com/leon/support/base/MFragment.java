package com.leon.support.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

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
import com.leon.support.base.model.mvp.IPresenter;
import com.leon.support.base.model.mvp.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * base {@link Fragment}
 * <br>
 */

public class MFragment extends Fragment implements MConstants,
        IAbilityAble, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic, IView {

    private AbilityDelegate mAbilityDelegate;
    private ILocalBroadcastHandler mLocalBroadcastHandler;
    private ISDKUIHelper mSDKUIHelper;
    private IAnalytic mAnalytic;
    private IPresenter mPresenter;

    protected static Bundle getBundle() {
        return new Bundle();
    }

    protected static Bundle getBundle(Parcelable parcelable) {
        Bundle bundle = getBundle();
        bundle.putParcelable(Extras.DATA, parcelable);
        return bundle;
    }

    protected static Bundle getBundle(Object... args) {
        Bundle bundle = getBundle();
        if (args != null) {
            String extra;
            Object arg;
            for (int i = 0; i < args.length; i++) {
                extra = "ext_data" + i;
                arg = args[i];
                if (arg instanceof String) {
                    bundle.putString(extra, (String) arg);
                } else if (arg instanceof Integer) {
                    bundle.putInt(extra, (Integer) arg);
                } else if (arg instanceof Parcelable) {
                    bundle.putParcelable(extra, (Parcelable) arg);
                } else if (arg instanceof Float) {
                    bundle.putFloat(extra, (Float) arg);
                } else if (arg instanceof Long) {
                    bundle.putLong(extra, (Long) arg);
                } else if (arg instanceof Boolean) {
                    bundle.putBoolean(extra, (Boolean) arg);
                } else if (arg instanceof List) {
                    bundle.putParcelableArrayList(extra, (ArrayList<? extends Parcelable>) arg);
                }
            }
        }
        return bundle;
    }


    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(context);
        mSDKUIHelper = new BaseUIHelperImpl();
        mAnalytic = onCreateAnalytic();
        mAbilityDelegate.onAttach();

    }

    protected IAnalytic onCreateAnalytic() {
        return new AnalyticHelper();
    }


    public void setupTitle(int title) {
        this.setupTitle(getString(title));
    }

    public void setupTitle(CharSequence title) {
        final Activity activity = getActivity();
        if (null != activity) {
            if (activity instanceof MToolBarActivity) {
                ((MToolBarActivity) activity).setupTitle(title);
            } else {
                activity.setTitle(title);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAbilityDelegate.onCreate(savedInstanceState);
        if (null != mPresenter) mPresenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAbilityDelegate.onStart();
        if (null != mPresenter) mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        onEVPageStart(getClass().getName());
        mAbilityDelegate.onResume();
        if (null != mPresenter) mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        onEVPageEnd(getClass().getName());
        mAbilityDelegate.onPause();
        if (null != mPresenter) mPresenter.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterAllLocalReceivers();
        mAbilityDelegate.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAbilityDelegate.onStop();
        if (null != mPresenter) mPresenter.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAbilityDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAbilityDelegate.onDestroy();
        if (null != mPresenter) mPresenter.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mAbilityDelegate.onHiddenChanged(hidden);
    }


    public MFragment() {
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

    @SuppressWarnings("unchecked")
    protected <P> P getPresenter() {
        return (P) mPresenter;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        if (null != mPresenter) {
            // view already has the presenter
            return;
        }
        this.mPresenter = presenter;
    }

}
