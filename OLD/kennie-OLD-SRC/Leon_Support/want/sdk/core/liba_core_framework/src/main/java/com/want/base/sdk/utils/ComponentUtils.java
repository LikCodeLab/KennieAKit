package com.want.base.sdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/7/22<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Components Utils.
 * <br>
 */
@SuppressWarnings("unused")
public class ComponentUtils {

    private ComponentUtils() {
        // hide
    }


    /**
     * Settings All {@link android.content.BroadcastReceiver}'s status to {@link PackageManager#COMPONENT_ENABLED_STATE_ENABLED}
     *
     * @param context
     */
    public static void enableAllReceiver(Context context) {
        enableAllReceiver(context, true);
    }

    /**
     * Settings All {@link android.content.BroadcastReceiver}'s status to {@link PackageManager#COMPONENT_ENABLED_STATE_DISABLED}
     *
     * @param context
     */
    public static void disableAllReceiver(Context context) {
        enableAllReceiver(context, false);
    }

    /**
     * Settings All {@link android.content.BroadcastReceiver}'s status.
     *
     * @param context
     * @param enable  Is enable.
     */
    public static void enableAllReceiver(Context context, boolean enable) {
        final PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pInfo = pm.getPackageInfo(context.getPackageName(),
                                                  PackageManager.GET_RECEIVERS);
            if (null != pInfo) {
                settingEnabledComponents(context,
                                         pm,
                                         pInfo.receivers,
                                         enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                                                 : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                         PackageManager.DONT_KILL_APP);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Setting the components({@link android.app.Activity}、{@link android.app.Service}、
     * {@link android.content.ContentProvider}、{@link android.content.BroadcastReceiver}) enable status.
     *
     * @param pm       {@link PackageManager}
     * @param infos    Arrays of {@link ActivityInfo}
     * @param newstate
     * @param flag
     */
    public static void settingEnabledComponents(Context context,
                                                PackageManager pm,
                                                ActivityInfo[] infos,
                                                int newstate,
                                                int flag) {
        for (ActivityInfo info : infos) {
            pm.setComponentEnabledSetting(new ComponentName(context, info.name), newstate, flag);
        }

    }

    /**
     * Setting the components({@link android.app.Activity}、{@link android.app.Service}、
     * {@link android.content.ContentProvider}、{@link android.content.BroadcastReceiver}) enable status.
     *
     * @param pm         {@link PackageManager}
     * @param components list of {@link ComponentName}
     * @param newstate   One of {@link PackageManager#COMPONENT_ENABLED_STATE_ENABLED}、
     *                   {@link PackageManager#COMPONENT_ENABLED_STATE_DISABLED}、
     *                   {@link PackageManager#COMPONENT_ENABLED_STATE_DEFAULT}
     * @param flag       One of {@link PackageManager#DONT_KILL_APP}、0
     */
    public static void settingEnabledComponents(PackageManager pm,
                                                List<ComponentName> components,
                                                int newstate,
                                                int flag) {
        for (ComponentName component : components) {
            pm.setComponentEnabledSetting(component, newstate, flag);
        }
    }
}
