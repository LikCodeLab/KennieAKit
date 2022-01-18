package com.kennie.utils.core;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

import com.kennie.utils.manager.UtilsManager;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：NotificationUtils
 * Date：2021/12/12 23:15
 * Desc：Notification通知管理工具类
 *
 * <p>
 * --判断通知功能是否开启                         {@link #isNotificationEnabled()}
 * --判断通知渠道是否开启                         {@link #isNotificationChannelEnabled(String channelId)}
 * </p>
 */
public class NotificationUtils {

    /**
     * 判断通知功能是否开启
     *
     * @return {@code true}: 已开启<br>{@code false}: 未开启
     */
    public static boolean isNotificationEnabled() {
        return NotificationManagerCompat.from(UtilsManager.get().getAppContext()).areNotificationsEnabled();
    }

    /**
     * 判断通知渠道是否开启（8.0以上） 暂时无效
     *
     * @param channelId 渠道ID
     * @return {@code true}: 已开启<br>{@code false}: 未开启
     */
    @Deprecated
    public static boolean isNotificationChannelEnabled(String channelId) {
        if (isNotificationEnabled()) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                NotificationManager notificationManager = (NotificationManager) UtilsManager.get().getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
                return notificationChannel == null || notificationChannel.getImportance() != NotificationManager.IMPORTANCE_NONE;
            }
        }

        return false;
    }
}
