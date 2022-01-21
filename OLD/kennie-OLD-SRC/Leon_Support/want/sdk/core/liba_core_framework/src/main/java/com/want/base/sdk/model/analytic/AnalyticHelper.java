package com.want.base.sdk.model.analytic;

import android.content.Context;

import com.want.base.sdk.model.analytic.impl.UmengAnalyticImpl;

import java.util.Map;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class AnalyticHelper implements IAnalytic {

    private static class SingleHolder {
        static IAnalytic INSTANCE = new UmengAnalyticImpl();
    }

    public AnalyticHelper() {

    }

    @Override
    public void openActivityDurationTrack(boolean track) {
        SingleHolder.INSTANCE.openActivityDurationTrack(track);
    }

    @Override
    public void onEVResume(Context context) {
        SingleHolder.INSTANCE.onEVResume(context);
    }

    @Override
    public void onEVPause(Context context) {
        SingleHolder.INSTANCE.onEVPause(context);
    }

    @Override
    public void onEVPageStart(String label) {
        SingleHolder.INSTANCE.onEVPageStart(label);
    }

    @Override
    public void onEVPageEnd(String label) {
        SingleHolder.INSTANCE.onEVPageEnd(label);
    }

    @Override
    public void onEVEvent(Context context, String label) {
        SingleHolder.INSTANCE.onEVEvent(context, label);
    }

    @Override
    public void onEVEvent(Context context, String label, Map<String, String> maps) {
        SingleHolder.INSTANCE.onEVEvent(context, label, maps);
    }

    @Override
    public void onEVEventValue(Context context,
                               String label,
                               Map<String, String> maps,
                               int duration) {
        SingleHolder.INSTANCE.onEVEventValue(context, label, maps, duration);
    }

}
