package com.want.base.sdk.model.ability;

import android.support.v4.app.Fragment;
import android.view.View;

import com.want.ability.SimpleAbltilyImpl;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/27<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ToolbarAbility extends SimpleAbltilyImpl {

    private Fragment mFragment;

    public ToolbarAbility(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void onAttach() {
        mFragment.setHasOptionsMenu(true);
        super.onAttach();
    }

    @Override
    public void onDetach() {
        mFragment.setHasOptionsMenu(false);
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        final View view = mFragment.getView();
        if (null != view) {
            view.setVisibility(hidden ? View.GONE : View.VISIBLE);
        }

        super.onHiddenChanged(hidden);
    }
}
