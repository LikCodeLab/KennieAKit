package com.learkoo.push.examples;

import android.app.Application;

import com.learkoo.push.agent.PushAgent;
import com.learkoo.push.jpush.JpushAgentImpl;


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent.getInstance().addAgent(new JpushAgentImpl());
        PushAgent.getInstance().setDebugMode(true);
        PushAgent.getInstance().init(getApplicationContext());
    }
}
