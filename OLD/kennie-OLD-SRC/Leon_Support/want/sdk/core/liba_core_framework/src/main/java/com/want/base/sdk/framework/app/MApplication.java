package com.want.base.sdk.framework.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;

import com.want.base.sdk.framework.Constants;
import com.want.base.sdk.framework.app.localbroadcast.ILocalBroadcastHandler;
import com.want.base.sdk.framework.app.localbroadcast.LocalBroadcastHandlerImpl;
import com.want.base.sdk.model.crash.AndroidCrashHandler;
import com.want.core.log.lg;

import java.util.List;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/7/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * base {@link Application}
 * <br>
 */
@SuppressWarnings("unused")
public class MApplication extends Application implements Constants, ILocalBroadcastHandler {

    private ILocalBroadcastHandler mLocalBroadcastHandler;

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
        final String pName = context.getApplicationContext().getPackageName();
        final int pID = android.os.Process.myPid();
        return pName.equalsIgnoreCase(getProcessName(context, pID));
    }


    /**
     * Do the application's init work in this method.
     * <p/>
     * <b><font color='red'>NOTE!!</font></b>
     * This method will be called many times if you have multi process.
     *
     * @param context context
     */
    protected void onInitApplication(Context context) {
        // init crash handler
        onSetupCrashHandler(context);
        // setup analytic
        onSetupAnalytic(context);
    }

    /**
     * Init the crash handler.
     *
     * @param context context
     */
    protected void onSetupCrashHandler(Context context) {
        AndroidCrashHandler.init(context);
        AndroidCrashHandler.REPORTLOG = lg.RELEASE;
    }

    /**
     * Setup the analytic
     *
     * @param context context
     */
    protected void onSetupAnalytic(Context context) {

    }

    /**
     * Do the application's init work in this method. And will be called only in the main process.
     *
     * @param context context
     */
    protected void onInitApplicationInMainProcess(Context context) {
//        MobclickAgent.setDebugMode(true);
//        MobclickAgent.setCatchUncaughtExceptions(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Context context = getApplicationContext();
        mLocalBroadcastHandler = new LocalBroadcastHandlerImpl(context);
        onInitApplication(context);

        if (isMainProcess(context)) {
            // init application in main process.
            onInitApplicationInMainProcess(context);
        }
    }


    /**
     * Get all the {@link BroadcastReceiver} register in the {@link LocalBroadcastManager}
     *
     * @return {@link BroadcastReceiver}'s list.
     */
    @Override
    public List<BroadcastReceiver> getRegisteredReceivers() {
        return mLocalBroadcastHandler.getRegisteredReceivers();
    }

    /**
     * Unregister all the {@link BroadcastReceiver} register in the {@link LocationManager}
     */
    @Override
    public void unRegisterAllLocalReceivers() {
        mLocalBroadcastHandler.unRegisterAllLocalReceivers();
    }

    /**
     * See {@link LocalBroadcastManager#registerReceiver(BroadcastReceiver, IntentFilter)}
     *
     * @param receiver
     * @param filter
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        mLocalBroadcastHandler.registerLocalReceiver(receiver, filter);
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver {@link BroadcastReceiver}
     * @param actions  string actions
     */
    @Override
    public void registerLocalReceiver(BroadcastReceiver receiver, String... actions) {
        mLocalBroadcastHandler.registerLocalReceiver(receiver, actions);
    }

    /**
     * See {@link LocalBroadcastManager#unregisterReceiver(BroadcastReceiver)}
     *
     * @param receiver
     */
    @Override
    public void unRegisterLocalReceiver(BroadcastReceiver receiver) {
        mLocalBroadcastHandler.unRegisterLocalReceiver(receiver);
    }

    @Override
    public boolean sendLocalBroadcast(Intent intent) {
        return mLocalBroadcastHandler.sendLocalBroadcast(intent);
    }

    /**
     * See {@link LocalBroadcastManager#sendBroadcastSync(Intent)}
     *
     * @param intent
     */
    @Override
    public void sendLocalBroadcastSync(Intent intent) {
        mLocalBroadcastHandler.sendLocalBroadcastSync(intent);
    }
}
