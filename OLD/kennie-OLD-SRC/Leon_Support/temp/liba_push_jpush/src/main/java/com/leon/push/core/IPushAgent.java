package com.leon.push.core;

import android.content.Context;

import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;


/**
 * <b>Project:</b> liba_push_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Push agent interface.
 * <br>
 */
public interface IPushAgent {

    /**
     * Callback with set tags or alias
     */
    interface TagAliasListener {
        /**
         * Called after tags or alias setted.
         *
         * @param status status code.
         * @param alias  alias
         * @param tags   tags
         */
        void onTagAliasCallback(int status, String alias, Set<String> tags);
    }

    String PUSH_SENDER = "com.leon.push.receiver.PushReceiver";

    /**
     * device registrted
     */
    String ACTION_REGISTRATION = "com.leon.push.ACTION_REGISTRATION";
    /**
     * custom message received
     */
    String ACTION_MESSAGE = "com.leon.push.ACTION_MESSAGE";
    /**
     * notification received
     */
    String ACTION_NOTIFICATION_RECEIVED = "com.leon.push.ACTION_NOTIFICATION_RECEIVED";
    /**
     * notification opened
     */
    String ACTION_NOTIFICATION_OPENED = "com.leon.push.ACTION_NOTIFICATION_OPENED";
    /**
     * rich message received
     */
    String ACTION_RICH_MESSAGE = "com.leon.push.ACTION_RICH_MESSAGE";

    /**
     * PUSH DATA
     */
    String PUSH_DATA = "push_data";


    String AGENT_JPUSH = "jpush";
    String AGENT_UMENG = "umeng";


    /***************初始化推送服务 API BEGIN****************/


    /**
     * init this push agent.
     *
     * @param context {@link Context}.
     */
    void init(Context context);

    /**
     * Config debug mode.
     *
     * @param debug boolean value.
     */
    void setDebugMode(boolean debug);


    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    void setPushCallback(IPushCallback callback);

    /**
     * Get the {@link IPushCallback} of this agent.
     *
     * @return
     */
    IPushCallback getPushCallback();


    /**
     * Get this agent registration id.
     * <p>
     * RegistrationID 定义
     * 集成了 JPush SDK 的应用程序在第一次成功注册到 JPush 服务器时，JPush 服务器会给客户端返回一个唯一的该设备的标识 - RegistrationID。JPush SDK 会以广播的形式发送 RegistrationID 到应用程序。
     * 应用程序可以把此 RegistrationID 保存以自己的应用服务器上，然后就可以根据 RegistrationID 来向设备推送消息或者通知。
     * <p>
     * 调用此 API 来取得应用程序对应的 RegistrationID。 只有当应用程序成功注册到 JPush 的服务器时才返回对应的值，否则返回空字符串。
     *
     * @param context context.
     */
    String getRegistrationID(Context context);


    /***************初始化推送服务 API END****************/


    /***************停止与恢复推送服务 API BEGIN****************/


    /**
     * API - stopPush Stop agent.
     * 停止推送服务。
     * 调用了本 API 后，JPush 推送服务完全被停止。具体表现为：
     * JPush Service 不在后台运行
     * 收不到推送消息
     * 极光推送所有的其他 API 调用都无效,不能通过 JPushInterface.init 恢复，需要调用resumePush恢复。
     *
     * @param context
     */
    void stop(Context context);


    /**
     * API - resumePush Resume agent.
     * 恢复推送服务。
     * 调用了此 API 后，极光推送完全恢复正常工作。
     *
     * @param context context.
     */
    void resume(Context context);


    /**
     * API - isPushStopped Wheather this agent is stoped.
     * 用来检查 Push Service 是否已经被停止
     * SDK 1.5.2 以上版本支持。
     *
     * @param context context.
     * @return true, is stoped.
     */
    boolean isStopped(Context context);


    /***************停止与恢复推送服务 API END****************/


    /***************别名与标签 API BEGIN****************/
    /****http://docs.jiguang.cn/jpush/client/Android/android_api/ ****************/
    /****温馨提示，设置标签别名请注意处理call back结果。只有call back 返回值为 0 才设置成功，才可以向目标推送。否则服务器 API 会返回1011错误。 ****************/

    /**
     * Set tags and alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param tags
     * @param listener
     */
    /**
     * 调用此 API 来同时设置别名与标签。
     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
     * 在之前调用过后，如果需要再次改变别名与标签，只需要重新调用此 API 即可。
     *
     * @param context
     * @param alias    null 此次调用不设置此值。（注：不是指的字符串"null")
     *                 "" （空字符串）表示取消之前的设置。
     *                 每次调用设置有效的别名，覆盖之前的设置。
     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持 @!#$&*+=.|。)
     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
     * @param tags     {@link Set} of tags.
     *                 null 此次调用不设置此值。（注：不是指的字符串"null"）
     *                 空数组或列表表示取消之前的设置。
     *                 每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
     *                 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
     *                 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，但总长度不得超过7K字节。（判断长度需采用UTF-8编码）
     * @param listener {@link TagAliasListener}.
     *                 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
     *                 错误码定义列表:http://docs.jiguang.cn/jpush/client/Android/android_api/#client_error_code
     */
    void setAliasAndTags(Context context, String alias, Set<String> tags, final TagAliasListener listener);


    /**
     * Set alias to agent.
     * 调用此 API 来设置别名。
     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
     *
     * @param context  context.
     * @param alias    "" （空字符串）表示取消之前的设置。
     *                 每次调用设置有效的别名，覆盖之前的设置。
     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
     * @param listener {@link TagAliasListener}.
     *                 在TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
     */
    void setAlias(Context context, String alias, final TagAliasListener listener);


    /**
     * Set tags to agent.
     * 调用此 API 来设置标签。
     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     *                 空数组或列表表示取消之前的设置。
     *                 每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
     *                 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
     *                 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，但总长度不得超过7K字节。（判断长度需采用UTF-8编码）
     *                 单个设备最多支持设置 1000 个 tag。App 全局 tag 数量无限制。
     * @param listener {@link TagAliasListener} 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
     * @使用建议 如果待设置的 alias / tags 是动态的，有可能在调用 setAliasAndTags 时因为 alias / tags 无效而整调用失败。
     * 调用此方法只设置 tags，可以排除可能无效的 alias 对本次调用的影响。
     */
    void setTags(Context context, Set<String> tags, final TagAliasListener listener);


    /***************别名与标签 API END****************/


    /***************设置允许推送时间 API BEGIN****************/

    /**
     * 默认情况下用户在任何时间都允许推送。即任何时候有推送下来，客户端都会收到，并展示。
     * 开发者可以调用此 API 来设置允许推送的时间。
     * 如果不在该时间段内收到消息，当前的行为是：推送到的通知会被扔掉。
     * 这是一个纯粹客户端的实现。
     * 所以与客户端时间是否准确、时区等这些，都没有关系。
     *
     * @param context   应用的ApplicationContext
     * @param weekDays  Set days 0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）
     *                  Sdk1.2.9 – 新功能:set的值为null,则任何时间都可以收到消息和通知，set的size为0，则表示任何时间都收不到消息和通知.
     * @param startHour int startHour 允许推送的开始时间 （24小时制：startHour的范围为0到23）
     * @param endHour   int endHour 允许推送的结束时间 （24小时制：endHour的范围为0到23）
     */
    void setPushTime(Context context, Set<Integer> weekDays, int startHour, int endHour);

    /***************设置允许推送时间 API END****************/


    /***************设置通知静默时间 API BEGIN****************/

    /**
     * 默认情况下用户在收到推送通知时，客户端可能会有震动，响铃等提示。但用户在睡觉、开会等时间点希望为 "免打扰" 模式，也是静音时段的概念。
     * 开发者可以调用此 API 来设置静音时段。如果在该时间段内收到消息，则：不会有铃声和震动。
     *
     * @param context     应用的ApplicationContext
     * @param startHour   静音时段的开始时间 - 小时 （24小时制，范围：0~23 ）
     * @param startMinute 静音时段的开始时间 - 分钟（范围：0~59 ）
     * @param endHour     静音时段的结束时间 - 小时 （24小时制，范围：0~23 ）
     * @param endMinute   静音时段的结束时间 - 分钟（范围：0~59 ）
     */
    void setSilenceTime(Context context, int startHour, int startMinute, int endHour, int endMinute);

    /***************设置通知静默时间 API END****************/


    /***************申请权限接口（Android 6.0 及以上） API BEGIN****************/

    /**
     * 在 Android 6.0 及以上的系统上，需要去请求一些用到的权限，JPush SDK 用到的一些需要请求如下权限，因为需要这些权限使统计更加精准，功能更加丰富，建议开发者调用。
     * "android.permission.READ_PHONE_STATE"
     * "android.permission.WRITE_EXTERNAL_STORAGE"
     * "android.permission.READ_EXTERNAL_STORAGE"
     * "android.permission.ACCESS_FINE_LOCATION"
     *
     * @param context 当前应用的 Activity 的上下文
     */
    void requestPermission(Context context);

    /***************申请权限接口（Android 6.0 及以上） API END****************/


    /***************通知栏样式定制 API BEGIN****************/
    //大多数情况下，开发者不需要调用这里的定制通知栏 API 来自定义通知栏样式，只需要使用 SDK 默认的即可。
    //如果您想：
    //改变 Notification 里的铃声、震动、显示与消失行为
    //自定义通知栏显示样式
    //不同的 Push 通知，Notification样式不同
    //则请使用本通知栏定制API提供的能力。
    //教程与代码示例 请参考文档：自定义通知栏样式教程(http://docs.jiguang.cn/jpush/client/Android/android_senior/#_8)

    /**
     * 设置默认通知栏样式构建类
     * 当用户需要定制默认的通知栏样式时，则可调用此方法。
     * 极光 Push SDK 提供了 2 个用于定制通知栏样式的构建类：
     * BasicPushNotificationBuilder:Basic 用于定制 Android Notification 里的 defaults / flags / icon 等基础样式（行为）
     * CustomPushNotificationBuilder:继承 Basic 进一步让开发者定制 Notification Layout
     * 如果不调用此方法定制，则极光Push SDK 默认的通知栏样式是：Android标准的通知栏提示。
     *
     * @param builder
     */
    void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder builder);


    /**
     * 设置某编号的通知栏样式构建类
     * 当开发者需要为不同的通知，指定不同的通知栏样式（行为）时，则需要调用此方法设置多个通知栏构建类。
     * 设置时，开发者自己维护 notificationBuilderId 这个编号，下发通知时使用 n_builder_id 指定该编号，从而 Push SDK 会调用开发者应用程序里设置过的指定编号的通知栏构建类，来定制通知栏样式。
     *
     * @param notificationBuilderId
     * @param builder
     */
    void setPushNotificationBuilder(Integer notificationBuilderId, BasicPushNotificationBuilder builder);

    /***************通知栏样式定制 API END****************/

    /***************设置保留最近通知条数 API BEGIN****************/
    //通过极光推送，推送了很多通知到客户端时，如果用户不去处理，就会有很多保留在那里。
    //新版本 SDK (v1.3.0) 增加此功能，限制保留的通知条数。默认为保留最近 5 条通知。
    //开发者可通过调用此 API 来定义为不同的数量。
    //仅对通知有效。所谓保留最近的，意思是，如果有新的通知到达，之前列表里最老的那条会被移除。
    //例如，设置为保留最近 5 条通知。假设已经有 5 条显示在通知栏，当第 6 条到达时，第 1 条将会被移除。


    /**
     * 本接口可以在 JPushInterface.init 之后任何地方调用。可以调用多次。SDK使用最后调用的数值。
     *
     * @param context 应用的 ApplicationContext
     * @param maxNum  最多显示的条数
     */
    void setLatestNotificationNumber(Context context, int maxNum);

    /***************设置保留最近通知条数 API END****************/


}
