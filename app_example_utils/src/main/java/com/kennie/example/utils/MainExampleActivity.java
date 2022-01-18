package com.kennie.example.utils;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.utils.AppIntentUtils;
import com.kennie.utils.AppStoreUtils;
import com.kennie.utils.AppUtils;
import com.kennie.utils.RegexUtils;
import com.kennie.utils.ScreenUtils;

public class MainExampleActivity extends AppCompatActivity {

    private static String TAG = MainExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_example);
        initUtils();
        initAppIntentUtils();
        initAppStoreUtils();
        initAppUtils();
        initRegexUtils();
        initScreenUtils();
    }


    private void initUtils() {
        Log.i(TAG, " ************************ initUtils BEGIN *************************** ");
        Log.i(TAG, " ************************ initUtils END *************************** ");

    }

    private void initAppIntentUtils() {
        Log.i(TAG, " ************************ initAppIntentUtils BEGIN *************************** ");
        findViewById(R.id.AppIntentUtils1).setOnClickListener(v -> AppIntentUtils.startApp("com.tencent.mm"));
        findViewById(R.id.AppIntentUtils2).setOnClickListener(v -> AppIntentUtils.startAppDetail(AppUtils.getAppPackage()));
        findViewById(R.id.AppIntentUtils3).setOnClickListener(v -> AppIntentUtils.startAppUninstall(AppUtils.getAppPackage()));
        findViewById(R.id.AppIntentUtils4).setOnClickListener(v -> AppIntentUtils.startSettings(Settings.ACTION_SETTINGS));
        findViewById(R.id.AppIntentUtils5).setOnClickListener(v -> AppIntentUtils.startBrowser("https://www.baidu.com"));
        findViewById(R.id.AppIntentUtils6).setOnClickListener(v -> AppIntentUtils.startBrowserBySearch("Android"));
        findViewById(R.id.AppIntentUtils7).setOnClickListener(v -> AppIntentUtils.startCallPhone("15062255123"));
        findViewById(R.id.AppIntentUtils8).setOnClickListener(v -> AppIntentUtils.startSendSMS("15062255123", "测试短信发送内容"));
        findViewById(R.id.AppIntentUtils9).setOnClickListener(v -> AppIntentUtils.startSendEmail("245794336@qq.com"));
        findViewById(R.id.AppIntentUtilsNotification).setOnClickListener(v -> AppIntentUtils.startAppNotification(AppUtils.getAppPackage()));

        Log.i(TAG, " ************************ initAppIntentUtils END *************************** ");
    }

    private void initAppStoreUtils() {
        Log.i(TAG, " ************************ initAppMarketUtils BEGIN *************************** ");
        Log.i(TAG, "判断应用市场是否存在 : " + AppStoreUtils.get().isMarketAvailable());
        Log.i(TAG, "判断当前应用是否来源Google Play : " + AppStoreUtils.get().isInstalledFromGooglePlay());
        findViewById(R.id.appStore1).setOnClickListener(v -> Log.i(TAG, "跳转到 应用商店:" + AppStoreUtils.get().startAutoAppStore("com.tencent.mm")));
        findViewById(R.id.appStore2).setOnClickListener(v -> Log.i(TAG, "跳转到 酷安 应用商店 ：" + AppStoreUtils.get().startAppStoreDetail("com.tencent.mm", AppStoreUtils.APP_STORE_PACKAGE.COOLAPK_PACKAGE_NAME)));
        Log.i(TAG, " ************************ initAppMarketUtils END *************************** ");
    }

    private void initAppUtils() {
        Log.i(TAG, " ************************ initAppUtils BEGIN *************************** ");
        Log.i(TAG, "获取APP名称 : " + AppUtils.getAppName());
        Log.i(TAG, "判断APP是否安装 : " + AppUtils.isInstalled("com.tencent.mm"));
        Log.i(TAG, " ************************ initAppUtils END *************************** ");
    }

    private void initRegexUtils() {
        Log.i(TAG, " ************************ RegexUtils BEGIN *************************** ");
        Log.i(TAG, "验证是否是手机号 : " + RegexUtils.isPhone("15062255123"));
        Log.i(TAG, "验证是否是邮箱 : " + RegexUtils.isEmail("245794336@qq.com"));
        Log.i(TAG, "验证是否是银行卡号 : " + RegexUtils.isBankCardNo("6224"));
        Log.i(TAG, "验证身份证号是否符合规则 : " + RegexUtils.isIDCardNo("6224"));
        Log.i(TAG, " ************************ RegexUtils END *************************** ");


    }

    private void initScreenUtils() {
        Log.i(TAG, " ************************ ScreenUtils BEGIN *************************** ");
        Log.i(TAG, "获取屏幕宽度 : " + ScreenUtils.getScreenWidth());
        Log.i(TAG, "获取屏幕宽度1：" + ScreenUtils.getScreenWidth1());
        Log.i(TAG, "获取屏幕高度 : " + ScreenUtils.getScreenHeight());
        Log.i(TAG, "获取屏幕高度1：" + ScreenUtils.getScreenHeight1());
        Log.i(TAG, "获取屏幕密度：" + ScreenUtils.getScreenDensity());

        Log.i(TAG, "获取状态栏高度：" + ScreenUtils.getStatusBarHeight());
        Log.i(TAG, "获取ActionBar高度：" + ScreenUtils.getActionBarHeight());

        Log.i(TAG, "获取导航栏高度：" + ScreenUtils.getNavigationBarHeight());
        Log.i(TAG, "获取导航栏高度1 : " + ScreenUtils.getNavigationBarHeight1());

        Log.i(TAG, "获取屏幕原始尺寸高度(包括虚拟功能键高度) : " + ScreenUtils.getDpi());
        Log.i(TAG, "判断设备是否有虚拟键 : " + ScreenUtils.isVirtualKey());

        Log.i(TAG, "dp转换为px：" + ScreenUtils.dp2px(48));
        Log.i(TAG, "sp转换为px：" + ScreenUtils.sp2px(48));
        Log.i(TAG, "px转换为dp：" + ScreenUtils.px2dp(48));

        Log.i(TAG, " ************************ ScreenUtils END *************************** ");

    }
}