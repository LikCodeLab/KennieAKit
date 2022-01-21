package com.want.base.sdk.framework.app.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/12/3<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Handler interface of {@link Toolbar}
 * <br>
 */
public interface IToolbarHandler {

    /**
     * Setup and config toolbar.
     *
     * @param appCompatActivity {@link AppCompatActivity}
     * @param toolbar           {@link Toolbar}
     */
    void setupToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar);

    /**
     * {@link android.app.Activity#onOptionsItemSelected(MenuItem)}.
     *
     * @param item {@link MenuItem}
     *
     * @return
     */
    boolean onOptionsItemSelected(MenuItem item);
}
