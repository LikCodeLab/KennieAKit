package com.learkoo.push.examples;

import android.app.Application;

import com.leon.push.agent.PushAgent;
import com.leon.push.jpush.JPushAgentImpl;


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent.getInstance().addAgent(new JPushAgentImpl());
        PushAgent.getInstance().setDebugMode(true);
        PushAgent.getInstance().init(getApplicationContext());
    }
}
