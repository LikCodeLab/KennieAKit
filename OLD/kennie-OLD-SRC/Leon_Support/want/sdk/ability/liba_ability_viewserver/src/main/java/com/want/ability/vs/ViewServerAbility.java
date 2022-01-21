package com.want.ability.vs;

import android.app.Activity;
import android.os.Bundle;

import com.android.debug.hv.ViewServer;
import com.want.ability.SimpleAbltilyImpl;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/14<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ViewServerAbility extends SimpleAbltilyImpl {

    private Activity mActivity;

    public ViewServerAbility(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        ViewServer.get(mActivity).addWindow(mActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(mActivity).setFocusedWindow(mActivity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(mActivity).removeWindow(mActivity);
    }
}