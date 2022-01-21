package com.leon.support.base;

import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.support.R;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Base Activity with {@link android.support.v7.widget.Toolbar}.
 * <br>
 */
public class MToolBarActivity extends MAppCompatActivity {


    /**
     * Set the default layout with only one FrameLayout.
     */
    protected void requestContentView() {
        setContentView(R.layout.base_fragment_activity);
    }

    /**
     * Overide this method and pass your content view with toolbar.
     *
     * @param inflater {@link LayoutInflater}
     * @return Content view with toolbar.
     */
    protected ViewGroup onCreateToolbarLayout(LayoutInflater inflater) {
        final View contentView = inflater.inflate(R.layout.base_toolbar_activity, null);
        if (!(contentView instanceof ViewGroup)) {
            throw new RuntimeException("Only ViewGroup will be accepted.");
        }
        return (ViewGroup) contentView;
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, null, true));
    }

    @Override
    public void setContentView(View view) {
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.setContentView(view, lp);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        final ViewGroup rootView = onCreateToolbarLayout(getLayoutInflater());
        ViewGroup contentView = (ViewGroup) rootView.findViewById(R.id.base_root);
        if (null != contentView) {
            contentView.addView(view, params);
        } else {
            rootView.addView(view, params);
        }
        super.setContentView(rootView, params);
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
