package com.want.base.sdk.framework.eventbus;

//import de.greenrobot.event.EventBus;
//import oms.mmc.app.BaseActionBarActivity;
//import oms.mmc.app.BaseActivity;
//import oms.mmc.app.fragment.BaseFragment;
//import oms.mmc.app.fragment.BaseFragmentActivity;

import de.greenrobot.event.EventBus;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/6/17<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * EventBus based on {@link EventBus}
 * <br>
 */
public class MEventBus {

    private static volatile MEventBus defaultInstance;

    private MEventBus() {
//        EventBus.builder()
//                .skipMethodVerificationFor(BaseFragment.class)
//                .skipMethodVerificationFor(BaseActionBarActivity.class)
//                .skipMethodVerificationFor(BaseActivity.class)
//                .skipMethodVerificationFor(BaseFragmentActivity.class)
//                .installDefaultEventBus();

    }

    public static EventBus getDefault() {
        if (null == defaultInstance) {
            synchronized (MEventBus.class) {
                if (null == defaultInstance) {
                    defaultInstance = new MEventBus();
                }
            }
        }
        return EventBus.getDefault();
    }


}
