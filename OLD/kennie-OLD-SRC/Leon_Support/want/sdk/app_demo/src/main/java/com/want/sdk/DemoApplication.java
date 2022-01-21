package com.want.sdk;

import com.umeng.analytics.MobclickAgent;
import com.want.base.sdk.framework.app.MApplication;
import com.want.core.log.lg;
import com.want.core.push.impl.jpush.JpushAgentImpl;
import com.want.push.PushAgent;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class DemoApplication extends MApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        lg.setAppTag("demo");
        MobclickAgent.setDebugMode(true);
        final PushAgent pushAgent = PushAgent.getInstance();
        pushAgent.addAgent(new JpushAgentImpl());
        pushAgent.setDebugMode(true);
        pushAgent.init(this);
    }
}
