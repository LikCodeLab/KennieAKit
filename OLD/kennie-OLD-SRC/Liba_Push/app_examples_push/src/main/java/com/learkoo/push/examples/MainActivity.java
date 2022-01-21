package com.learkoo.push.examples;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private TextView mChannelText ;
    private TextView mAppKeyText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChannelText = (TextView)findViewById(R.id.channel_text);
        mAppKeyText = (TextView)findViewById(R.id.appkey_text);
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            String jpush_channel = appInfo.metaData.getString("JPUSH_CHANNEL");
            String jpush_appkey = appInfo.metaData.getString("JPUSH_APPKEY");
            mChannelText.setText(jpush_channel);
            mAppKeyText.setText(jpush_appkey);
            Log.d(TAG, " 获取当前极光推送 msg == " + jpush_channel + "," + jpush_appkey);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }
}
