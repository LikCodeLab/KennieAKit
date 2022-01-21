package com.want.base.sdk.framework.app;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.want.base.sdk.R;


/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/27<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Base Activity with {@link android.support.v7.widget.Toolbar}.
 * <br>
 */
@SuppressLint("Registered")
public class MToolbarActivity extends MFragmentActivity {

    /**
     * Set the default layout with only one FrameLayout.
     */
    protected void requestContentView() {
        setContentView(R.layout.sdk_base_fragment_activity);
    }

    /**
     * Overide this method and pass your content view with toolbar.
     *
     * @param inflater {@link LayoutInflater}
     *
     * @return Content view with toolbar.
     */
    protected ViewGroup onCreateToolbarLayout(LayoutInflater inflater) {
        final View contentView = inflater.inflate(R.layout.sdk_base_toolbar_activity, null);
        if (!(contentView instanceof ViewGroup)) {
            throw new RuntimeException("Only ViewGroup will be accepted.");
        }
        return (ViewGroup) contentView;
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                     ViewGroup.LayoutParams.MATCH_PARENT);
        this.setContentView(view, lp);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        final ViewGroup contentView = onCreateToolbarLayout(getLayoutInflater());
        contentView.addView(view, params);
        super.setContentView(contentView, params);
    }

    public void setupTitle(CharSequence title) {
        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle(title);
        }
    }

    public void setupTitle(int title) {
        setupTitle(getString(title));
    }
}
