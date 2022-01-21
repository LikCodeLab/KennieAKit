package com.want.base.sdk.framework.app.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Runable run on the UI thread.
 * <br>
 */
public abstract class UIRunable implements Runnable {

    private Activity mActivity;
    private Fragment mFragment;

    private Context mContext;

    private final boolean useActivity;

    public UIRunable(Activity activity) {
        this.mActivity = activity;
        useActivity = true;
    }

    public UIRunable(Fragment fragment) {
        this.mFragment = fragment;
        useActivity = false;
    }

    protected Context getContext() {
        if (null != mContext) {
            return mContext;
        }

        if (useActivity) {
            mContext = mActivity;
        } else {
            mContext = mFragment.getActivity();
        }

        return mContext;
    }

    /**
     * Run on the UI thread.
     *
     * @param context context.
     */
    protected abstract void runOnUiThread(Context context);

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public final void run() {
        if (useActivity && null != mActivity && !mActivity.isFinishing()) {
            runOnUiThread(getContext());
        } else if (!useActivity && null != mFragment && !mFragment.isDetached()) {
            runOnUiThread(getContext());
        }
    }
}
