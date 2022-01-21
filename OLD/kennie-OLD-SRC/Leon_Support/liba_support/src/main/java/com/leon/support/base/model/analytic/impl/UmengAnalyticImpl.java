package com.leon.support.base.model.analytic.impl;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.leon.support.base.model.analytic.IAnalytic;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> leon<br>
 * <b>Description:</b>
 * Analytic implements of Umeng
 * <br>
 */
public class UmengAnalyticImpl implements IAnalytic {

    private boolean isOpenActivityDurationTrack = true;

    /** 是否包含碎片 */
    private boolean hasFragments = false;

    @Override
    public void openActivityDurationTrack(boolean track) {
        this.isOpenActivityDurationTrack = track;
        MobclickAgent.openActivityDurationTrack(track);
    }

    @Override
    public void onEVResume(Context context) {
        MobclickAgent.onResume(context);
        if (!isOpenActivityDurationTrack) {
            if (context instanceof FragmentActivity) {
                final FragmentActivity activity = (FragmentActivity) context;
                final FragmentManager fm = activity.getSupportFragmentManager();
                hasFragments = null != fm.getFragments();
                if (!hasFragments) {
                    onEVPageStart(context.getClass().getName());
                }
            }
        }
    }

    @Override
    public void onEVPause(Context context) {
        MobclickAgent.onPause(context);
        if (!isOpenActivityDurationTrack) {
            if (!hasFragments) {
                onEVPageEnd(context.getClass().getName());
            }
        }
    }

    @Override
    public void onEVPageStart(String label) {
        MobclickAgent.onPageStart(label);
    }

    @Override
    public void onEVPageEnd(String label) {
        MobclickAgent.onPageEnd(label);
    }

    @Override
    public void onEVEvent(Context context, String label) {
        MobclickAgent.onEvent(context, label);
    }

    @Override
    public void onEVEvent(Context context, String label, Map<String, String> maps) {
        MobclickAgent.onEvent(context, label, maps);
    }

    @Override
    public void onEVEventValue(Context context,
                               String label,
                               Map<String, String> maps,
                               int duration) {
        MobclickAgent.onEventValue(context, label, maps, duration);
    }


}
