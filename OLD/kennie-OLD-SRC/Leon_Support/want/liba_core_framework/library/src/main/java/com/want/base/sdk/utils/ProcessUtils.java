package com.want.base.sdk.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * <b>Create Date:</b> 9/12/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ProcessUtils {

    private ProcessUtils() {
        //no instance
    }

    /**
     * Get the process's name by process id.
     *
     * @param context context
     * @param pid     process id
     *
     * @return the process name, usually you get the applicaiton's package name.
     */
    public static String getProcessName(Context context, int pid) {
        String processName = context.getPackageName();

        ActivityManager actvityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo procInfo : procInfos) {
            if (pid == procInfo.pid) {
                processName = procInfo.processName;
            }
        }
        return processName;
    }

    /**
     * whether the main process. By default, the main process is your application's package name.
     *
     * @param context context
     *
     * @return
     */
    public static boolean isMainProcess(Context context) {
        final String pName;
        if (context instanceof Application) {
            pName = context.getPackageName();
        } else {
            pName = context.getApplicationContext().getPackageName();
        }

        final int pID = android.os.Process.myPid();
        return pName.equalsIgnoreCase(getProcessName(context, pID));
    }

    /**
     * Get the process id of the special process name.
     *
     * @param context     context
     * @param processName process name
     *
     * @return process id
     */
    public static int getProcessId(Context context, String processName) {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.processName.equals(processName)) {
                return info.pid;
            }
        }

        return -1;
    }
}
