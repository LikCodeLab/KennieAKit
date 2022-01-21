package com.leon.support.base.model.ability;

import android.support.v4.app.Fragment;
import android.view.View;

import com.leon.support.base.model.ability.core.BaseAbilityImpl;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */
public class ToolbarAbility extends BaseAbilityImpl {

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
