package com.want.base.sdk.framework.app.toolbar;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/12/3<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ToolbarHandlerImpl implements IToolbarHandler {

    private Activity mActivity;

    /**
     * Setup and config toolbar.
     *
     * @param appCompatActivity {@link AppCompatActivity}
     * @param toolbar           {@link Toolbar}
     */
    @Override
    public void setupToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        this.mActivity = appCompatActivity;
        appCompatActivity.setSupportActionBar(toolbar);
        final ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(appCompatActivity.getTitle());
        }
    }

    /**
     * {@link Activity#onOptionsItemSelected(MenuItem)}.
     *
     * @param item {@link MenuItem}
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (android.R.id.home == id) {
            mActivity.onBackPressed();
            return true;
        }

        return false;
    }
}
