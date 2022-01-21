package com.leon.push.agent;

import android.content.Context;

import com.leon.push.agent.callback.DefaultPushCallback;
import com.leon.push.core.IPushAgent;
import com.leon.push.core.IPushCallback;

import java.util.ArrayList;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;


/**
 * <b>Project:</b> liba_push_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Push client.
 * <br>
 */
public class PushAgent implements IPushAgent {
    private ArrayList<IPushAgent> mAgentList = new ArrayList<>();
    private final Object mAddLock = new Object();

    private IPushCallback mDefaultPushCallback = new DefaultPushCallback();

    private IPushAgent mDefaultPushAgent;

    private static class Singleton {
        static PushAgent INSTANCE = new PushAgent();
    }

    private PushAgent() {
        // hide
    }

    public static PushAgent getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public IPushCallback getPushCallback() {
        return this.mDefaultPushCallback;
    }

    /**
     * Set tags to agent.
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}
     */
    @Override
    public void setTags(Context context, Set<String> tags, TagAliasListener listener) {
        mDefaultPushAgent.setTags(context, tags, listener);
    }

    @Override
    public void setPushTime(Context context, Set<Integer> weekDays, int startHour, int endHour) {
        mDefaultPushAgent.setPushTime(context, weekDays, startHour, endHour);
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
        mDefaultPushAgent.setSilenceTime(context, startHour, startMinute, endHour, endMinute);
    }

    /**
     * @param context 当前应用的 Activity 的上下文
     */
    @Override
    public void requestPermission(Context context) {
        mDefaultPushAgent.requestPermission(context);
    }

    /**
     * @param builder
     */
    @Override
    public void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder builder) {
        mDefaultPushAgent.setDefaultPushNotificationBuilder(builder);
    }

    /**
     *
     * @param notificationBuilderId
     * @param builder
     */
    @Override
    public void setPushNotificationBuilder(Integer notificationBuilderId, BasicPushNotificationBuilder builder) {
        mDefaultPushAgent.setPushNotificationBuilder(notificationBuilderId, builder);
    }

    /**
     *
     * @param context 应用的 ApplicationContext
     * @param maxNum  最多显示的条数
     */
    @Override
    public void setLatestNotificationNumber(Context context, int maxNum) {
        mDefaultPushAgent.setLatestNotificationNumber(context, maxNum);
    }

    /**
     * Set alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAlias(Context context, String alias, TagAliasListener listener) {
        mDefaultPushAgent.setAlias(context, alias, listener);
    }

    /**
     * @param context
     * @param alias    null 此次调用不设置此值。（注：不是指的字符串"null")
     *                 "" （空字符串）表示取消之前的设置。
     *                 每次调用设置有效的别名，覆盖之前的设置。
     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持 @!#$&*+=.|。)
     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAliasAndTags(Context context,
                                String alias,
                                Set<String> tags,
                                TagAliasListener listener) {
        mDefaultPushAgent.setAliasAndTags(context, alias, tags, listener);
    }

    /**
     * Resume agent.
     *
     * @param context context.
     */
    @Override
    public void resume(Context context) {
        mDefaultPushAgent.resume(context);
    }

    /**
     * Stop agent.
     *
     * @param context context.
     */
    @Override
    public void stop(Context context) {
        mDefaultPushAgent.stop(context);
    }

    /**
     * Wheather this agent is stoped.
     *
     * @param context context.
     * @return true, is stoped.
     */
    @Override
    public boolean isStopped(Context context) {
        return mDefaultPushAgent.isStopped(context);
    }

    /**
     * @param context context.
     * @return
     */
    @Override
    public String getRegistrationID(Context context) {
        //SDK 初次注册成功后，开发者通过在自定义的 Receiver 里监听 Action
        //cn.jpush.android.intent.REGISTRATION 来获取对应的 RegistrationID。注册成功后，也可以通过此函数获取
        return mDefaultPushAgent.getRegistrationID(context);
    }

    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    @Override
    public void setPushCallback(IPushCallback callback) {
        this.mDefaultPushCallback = callback;
    }

    /**
     * Get push callback with target pushagent.
     *
     * @param iPushAgent target pushagent.
     * @return the target pushagent or default pushcallback.
     */
    public IPushCallback getPushCallback(IPushAgent iPushAgent) {
        final IPushCallback callback = iPushAgent.getPushCallback();
        if (null != callback) {
            return callback;
        }
        return mDefaultPushCallback;
    }


    /**
     * Add a push agent. You must call this method before call {@link #init(Context)}
     *
     * @param agent {@link IPushAgent}
     */
    public void addAgent(IPushAgent agent) {
        for (IPushAgent pushInterface : mAgentList) {
            // already add agent, so break.
            if (pushInterface.getClass().isInstance(agent)) {
                return;
            }
        }

        synchronized (mAddLock) {
            mAgentList.add(agent);
            if (null == mDefaultPushAgent) {
                mDefaultPushAgent = agent;
            }
        }
    }

    /**
     * Set the default push agent.
     *
     * @param agent {@link IPushAgent}.
     */
    public void setDefaultAgent(IPushAgent agent) {
        if (!mAgentList.contains(agent)) {
            throw new IllegalArgumentException("agent must one of the instances in this PushAgent.");
        }

        mDefaultPushAgent = agent;
    }

    /**
     * Set debug mode
     *
     * @param debug true, in debug.
     */
    @Override
    public void setDebugMode(boolean debug) {
        innersetDebugMode(debug);
    }

    private void innersetDebugMode(boolean debug) {
        for (IPushAgent iPushAgent : mAgentList) {
            iPushAgent.setDebugMode(debug);
        }
    }

    /**
     * Init the push implements.
     *
     * @param context {@link Context}.
     */
    @Override
    public void init(Context context) {
        innerInit(context);
    }

    private void innerInit(Context context) {
        for (IPushAgent iPushAgent : mAgentList) {
            iPushAgent.init(context);
        }
    }
}
