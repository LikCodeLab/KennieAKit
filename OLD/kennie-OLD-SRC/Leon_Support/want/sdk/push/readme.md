##PUSH 模块使用说明

###快速使用

1. 添加依赖：

        // push
        compile 'com.want.model:push-core:0.1.0-SNAPSHOT@aar'
        compile 'com.want.model:push-agent:0.1.0-SNAPSHOT@aar'
        // jpush or other pushagent
        compile 'com.want.model:push-jpush:0.1.0-SNAPSHOT@aar'
        //- end



2. 添加代码（示例）：

        final PushAgent pushAgent = PushAgent.getInstance();
        pushAgent.addAgent(new JpushAgentImpl());
        pushAgent.setDebugMode(true);
        pushAgent.init(this);

3. 其他配置：

    如果使用了 Jpush，则需要在 AndroidManifest.xml 文件中添加以下代码：

        <!-- Jpush-->
        <meta-data
        android:name="JPUSH_APPKEY"
        android:value="your appkey"
        tools:replace="android:value"/>


###PushAgent

> PushAgent是整个 push 系统的入口，通过该类可以配置具体的 push 实现，具体的 callback。提供以下方法:
    
1. addAgent(ipushAgent)
添加指定的 IPushAgent。push 模块基于面向接口设计，在使用时需要指定具体的 push 客户端实现。调用此方法允许添加多个不同的 push 客户端。

2. setPushCallback(ipushcallback)
设置指定的 IPushCallback。push 模块允许根据业务指定不同的消息回调接口。默认情况下内部会自动实例化一个默认实现的 IPushCallback。

3. getPushCallback()
返回设定的 IPushCallback。

4. setDebugMode(debug)
设定是否开启调试模式。便于观察日志查看 push 系统运行信息。

5. init(context)
初始化 push 系统。

6. setTags(context, tags, tagAliasListener)
配置 TAG 信息。

7. setAlias（context, alias, tagAliasListener)
配置别名。

8. setTagsAndAlias(context, alias, tags, tagAliasListener)
配置别名和 TAG 信息。

9. resume()
恢复push 模块。

10. stop()
停止 push 模块。

11. isStoped()
push 模块是否已经停止运行。

12. getRegistrationID()
获取注册到 push 服务器的 ID。
    

###IPushCallback

> IPushCallback 提供针对推送信息的回调的接口。

方法信息如下：

    /**
     * Called after device registration.
     *
     * @param context context
     * @param id      registration id.
     */
    void onRegistration(Context context, String id);

    /**
     * Called after device unregistration.
     *
     * @param context context
     * @param id      registration id
     */
    void onUnRegistration(Context context, String id);

    /**
     * Called after custom message received.
     *
     * @param context context
     * @param data    json formated data
     */
    void onMessage(Context context, String data);

    /**
     * Called after rich message received.
     *
     * @param context context
     * @param data    json formated data
     */
    void onRichMessage(Context context, String data);

    /**
     * Called after notification received.
     *
     * @param context context
     * @param id      notification id
     */
    void onNotificationReceived(Context context, String id);

    /**
     * Called after notification opened
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    void onNotificationOpened(Context context, Bundle data);

    /**
     * Called after data not be handled.
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    void onUnHandledMessage(Context context, Bundle data);

系统已经默认实现了一个 DefaultPushCallback，用于处理常规的推送。你可以继承该类或者完全实现 IPushCallback 接口实现业务指定的消息回调接口。

###Jpush

系统已经集成了 Jpush 的实现。



