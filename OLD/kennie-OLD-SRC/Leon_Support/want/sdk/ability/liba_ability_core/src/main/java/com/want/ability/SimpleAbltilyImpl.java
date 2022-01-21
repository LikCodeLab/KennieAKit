package com.want.ability;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/27<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Default {@link IAbility} implements.
 * <br>
 */
public class SimpleAbltilyImpl implements IAbility {
    /**
     * {@link Activity#onCreate(Bundle)}、{@link Fragment#onCreate(Bundle)}
     *
     * @param savedBundle
     */
    @Override
    public void onCreate(Bundle savedBundle) {

    }

    /**
     * {@link Activity#onAttachedToWindow()}、{@link Fragment#onAttach(Context)}
     */
    @Override
    public void onAttach() {

    }

    /**
     * {@link Activity#onDetachedFromWindow()}、{@link Fragment#onDetach()}
     */
    @Override
    public void onDetach() {

    }

    /**
     * {@link Activity#onStart()}、{@link Fragment#onStart()}
     */
    @Override
    public void onStart() {

    }

    /**
     * {@link Activity#onResume()}、{@link Fragment#onResume()}
     */
    @Override
    public void onResume() {

    }

    /**
     * {@link Activity#onPause()}、{@link Fragment#onPause()}
     */
    @Override
    public void onPause() {

    }

    /**
     * {@link Activity#onSaveInstanceState(Bundle)}、{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * {@link Activity#onPostCreate(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }

    /**
     * {@link Activity#onStop()}、{@link Fragment#onStop()}
     */
    @Override
    public void onStop() {

    }

    /**
     * {@link Activity#onDestroy()}、{@link Fragment#onDestroy()}
     */
    @Override
    public void onDestroy() {

    }

    /**
     * {@link Fragment#onHiddenChanged(boolean)}
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {

    }
}
