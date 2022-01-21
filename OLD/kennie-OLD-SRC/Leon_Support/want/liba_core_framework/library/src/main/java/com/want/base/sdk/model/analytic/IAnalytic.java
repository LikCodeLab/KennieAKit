package com.want.base.sdk.model.analytic;

import android.content.Context;

import java.util.Map;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/19<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IAnalytic {

    /**
     * Open activity duration track.
     *
     * @param track true, open; false, otherwise.
     */
    void openActivityDurationTrack(boolean track);

    /**
     * Activity resumed.
     *
     * @param context {@link android.app.Activity}
     */
    void onEVResume(Context context);

    /**
     * Activity paused.
     *
     * @param context {@link android.app.Activity}
     */
    void onEVPause(Context context);

    /**
     * Page(like: {@link android.app.Activity}、{@link android.support.v4.app.Fragment}) started.
     *
     * @param label page name.
     */
    void onEVPageStart(String label);

    /**
     * Page(like: {@link android.app.Activity}、{@link android.support.v4.app.Fragment}) stoped.
     *
     * @param label page name.
     */
    void onEVPageEnd(String label);

    /**
     * Event
     *
     * @param context context
     * @param label   event label
     */
    void onEVEvent(Context context, String label);

    /**
     * Event
     *
     * @param context context
     * @param label   label
     * @param maps    value maps
     */
    void onEVEvent(Context context, String label, Map<String, String> maps);

    /**
     * Event
     *
     * @param context  context
     * @param label    label
     * @param maps     value maps
     * @param duration duration
     */
    void onEVEventValue(Context context, String label, Map<String, String> maps, int duration);


}
