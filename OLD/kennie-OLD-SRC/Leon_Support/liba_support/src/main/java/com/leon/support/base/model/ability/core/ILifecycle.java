package com.leon.support.base.model.ability.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Lifecycle from {@link Activity} and {@link Fragment}
 * <br>
 */
public interface ILifecycle {

    /**
     * {@link Activity#onCreate(Bundle)}、{@link Fragment#onCreate(Bundle)}
     *
     * @param savedInstanceState
     */
    void onCreate(Bundle savedInstanceState);

    /**
     * {@link Activity#onAttachedToWindow()}、{@link Fragment#onAttach(Context)}
     */
    void onAttach();

    /**
     * {@link Activity#onDetachedFromWindow()}、{@link Fragment#onDetach()}
     */
    void onDetach();

    /**
     * {@link Activity#onStart()}、{@link Fragment#onStart()}
     */
    void onStart();

    /**
     * {@link Activity#onResume()}、{@link Fragment#onResume()}
     */
    void onResume();

    /**
     * {@link Activity#onPause()}、{@link Fragment#onPause()}
     */
    void onPause();

    /**
     * {@link Activity#onSaveInstanceState(Bundle)}、{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * {@link Activity#onPostCreate(Bundle)}
     *
     * @param savedInstanceState
     */
    void onPostCreate(Bundle savedInstanceState);

    /**
     * {@link Activity#onStop()}、{@link Fragment#onStop()}
     */
    void onStop();

    /**
     * {@link Activity#onDestroy()}、{@link Fragment#onDestroy()}
     */
    void onDestroy();

    /**
     * {@link Fragment#onHiddenChanged(boolean)}
     * @param hidden
     */
    void onHiddenChanged(boolean hidden);

}
