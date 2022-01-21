package com.leon.push.jpush;

import android.content.Context;

import com.leon.push.core.IPushAgent;
import com.leon.push.core.IPushCallback;

import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * <b>Project:</b> liba_push_jpush<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Jpush implements.
 * <br>
 */
public class JPushAgentImpl implements IPushAgent {


    /**
     * Config debug mode.
     *
     * @param debug boolean value.
     */
    @Override
    public void setDebugMode(boolean debug) {
        JPushInterface.setDebugMode(debug);
    }


    /**
     * Stop agent.
     * 停止推送服务。
     * 调用了本 API 后，JPush 推送服务完全被停止。具体表现为：
     * JPush Service 不在后台运行
     * 收不到推送消息
     * 极光推送所有的其他 API 调用都无效,不能通过 JPushInterface.init 恢复，需要调用resumePush恢复。
     *
     * @param context
     */
    @Override
    public void stop(Context context) {
        JPushInterface.stopPush(context);
    }


    /**
     * Resume agent.
     *
     * @param context context.
     */
    @Override
    public void resume(Context context) {
        JPushInterface.resumePush(context);
    }


    /**
     * Wheather this agent is stoped.
     *
     * @param context context.
     */
    @Override
    public boolean isStopped(Context context) {
        return JPushInterface.isPushStopped(context);
    }

    /**
     * init this push agent.
     *
     * @param context {@link Context}.
     */
    @Override
    public void init(Context context) {
        JPushInterface.init(context);
    }

    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    @Override
    public void setPushCallback(IPushCallback callback) {

    }

    /**
     * Get the {@link IPushCallback} of this agent.
     *
     * @return
     */
    @Override
    public IPushCallback getPushCallback() {
        return null;
    }

    /**
     * Set tags to agent.
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}
     */
    @Override
    public void setTags(Context context, Set<String> tags, final TagAliasListener listener) {
        JPushInterface.setTags(context, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int status, String alias, Set<String> tags) {
                if (null != listener) {
                    listener.onTagAliasCallback(status, alias, tags);
                }
            }
        });
    }

    /**
     * @param context   应用的ApplicationContext
     * @param weekDays  Set days 0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）
     *                  Sdk1.2.9 – 新功能:set的值为null,则任何时间都可以收到消息和通知，set的size为0，则表示任何时间都收不到消息和通知.
     * @param startHour int startHour 允许推送的开始时间 （24小时制：startHour的范围为0到23）
     * @param endHour   int endHour 允许推送的结束时间 （24小时制：endHour的范围为0到23）
     */
    @Override
    public void setPushTime(Context context, Set<Integer> weekDays, int startHour, int endHour) {
        JPushInterface.setPushTime(context, weekDays, startHour, endHour);
    }

    /**
     * @param context     应用的ApplicationContext
     * @param startHour   静音时段的开始时间 - 小时 （24小时制，范围：0~23 ）
     * @param startMinute 静音时段的开始时间 - 分钟（范围：0~59 ）
     * @param endHour     静音时段的结束时间 - 小时 （24小时制，范围：0~23 ）
     * @param endMinute   静音时段的结束时间 - 分钟（范围：0~59 ）
     */
    @Override
    public void setSilenceTime(Context context, int startHour, int startMinute, int endHour, int endMinute) {
        JPushInterface.setSilenceTime(context, startHour, startMinute, endHour, endMinute);
    }

    /**
     * @param context 当前应用的 Activity 的上下文
     */
    @Override
    public void requestPermission(Context context) {
        JPushInterface.requestPermission(context);
    }

    /**
     * @param builder
     */
    @Override
    public void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder builder) {
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }

    /**
     * @param notificationBuilderId
     * @param builder
     */
    @Override
    public void setPushNotificationBuilder(Integer notificationBuilderId, BasicPushNotificationBuilder builder) {
        JPushInterface.setPushNotificationBuilder(notificationBuilderId, builder);
    }

    /**
     * @param context 应用的 ApplicationContext
     * @param maxNum  最多显示的条数
     */
    @Override
    public void setLatestNotificationNumber(Context context, int maxNum) {
        JPushInterface.setLatestNotificationNumber(context, maxNum);
    }

    /**
     * Set alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAlias(Context context, String alias, final TagAliasListener listener) {
        JPushInterface.setAlias(context, alias, new TagAliasCallback() {
            @Override
            public void gotResult(int status, String alias, Set<String> tags) {
                if (null != listener) {
                    listener.onTagAliasCallback(status, alias, tags);
                }
            }
        });
    }

    /**
     * Set tags and alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAliasAndTags(Context context,
                                String alias,
                                Set<String> tags,
                                final TagAliasListener listener) {
        JPushInterface.setAliasAndTags(context, alias, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int status, String alias, Set<String> tags) {
                if (null != listener) {
                    listener.onTagAliasCallback(status, alias, tags);
                }
            }
        });
    }


    /**
     * Get this agent registration id.
     *
     * @param context context.
     */
    @Override
    public String getRegistrationID(Context context) {
        return JPushInterface.getRegistrationID(context);
    }
}
