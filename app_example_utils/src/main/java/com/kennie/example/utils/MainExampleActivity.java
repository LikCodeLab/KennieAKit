package com.kennie.example.utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.kennie.utils.core.AppIntentUtils;
import com.kennie.utils.core.AppStoreUtils;
import com.kennie.utils.core.DataUtils;
import com.kennie.utils.core.FileUtils;
import com.kennie.utils.core.RegexUtils;
import com.kennie.utils.core.AppUtils;
import com.kennie.utils.core.LogUtils;
import com.kennie.utils.core.NotificationUtils;
import com.kennie.utils.core.ScreenUtils;
import com.kennie.utils.helper.PathHelper;
import com.kennie.utils.helper.ToastHelper;

@SuppressLint("SetTextI18n")
public class MainExampleActivity extends AppCompatActivity {

    private static String TAG = MainExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_example);
        initUtils();
        initAppUtils();
        initDataUtils();
        initFileUtils();
        initRegexUtils();
        // 辅助日志管理工具类
        // initLogUtils();

        // Toast工具类
        // initToastUtils();


        initNotificationUtils();


//        initAppIntentUtils();
//        initAppStoreUtils();


//        initScreenUtils();
    }




    @SuppressLint("SetTextI18n")
    private void initAppUtils() {
        Log.i(TAG, " ************************ initAppUtils BEGIN *************************** ");
        AppCompatButton AppUtils0 = (AppCompatButton) findViewById(R.id.AppUtils0);
        AppCompatButton AppUtils1 = (AppCompatButton) findViewById(R.id.AppUtils1);
        AppCompatButton AppUtils2 = (AppCompatButton) findViewById(R.id.AppUtils2);
        AppCompatButton AppUtils3 = (AppCompatButton) findViewById(R.id.AppUtils3);
        AppCompatButton AppUtils4 = (AppCompatButton) findViewById(R.id.AppUtils4);
        AppCompatButton AppUtils5 = (AppCompatButton) findViewById(R.id.AppUtils5);
        AppCompatButton AppUtils6 = (AppCompatButton) findViewById(R.id.AppUtils6);
        AppCompatButton AppUtils7 = (AppCompatButton) findViewById(R.id.AppUtils7);
        AppCompatButton AppUtils8 = (AppCompatButton) findViewById(R.id.AppUtils8);
        AppCompatButton AppUtils9 = (AppCompatButton) findViewById(R.id.AppUtils9);
        AppCompatButton AppUtils10 = (AppCompatButton) findViewById(R.id.AppUtils10);

        AppUtils0.setText("获取APP名称 : " + AppUtils.getAppName());
        AppUtils1.setText("APP包名 : " + AppUtils.getAppPackage());
        AppUtils2.setText("App版本名称 ：" + AppUtils.getAppVersionName());
        AppUtils3.setText("App版本号 ：" + AppUtils.getAppVersionCode());
        Log.i(TAG, "App图标 ：");
        AppUtils4.setBackground(AppUtils.getAppIcon());
        AppUtils5.setText("App安装原始路径：" + AppUtils.getAppInstallPath());
        AppUtils6.setText("App原始安装文件(APK)：" + AppUtils.getAppSourceFile());
        AppUtils7.setText("APP是否为Debug模式 ：" + AppUtils.isAppDebugMode());
        AppUtils8.setText("App是否处于前台 ：" + AppUtils.isAppTopForeground());
        AppUtils9.setText("判断APP是否安装 : " + AppUtils.isInstalled("com.tencent.mm"));
        AppUtils10.setText("安装APP（兼容Android7.0及以上版本）: " + PathHelper.getExternalStoragePath() + "/1.apk");
        //AppUtils10.setOnClickListener(v -> AppUtils.installApk(new File(PathHelper.getExternalStoragePath() + "/1.apk"), ""));
        Log.i(TAG, " ************************ initAppUtils END *************************** ");
    }

    private void initDataUtils() {
        Log.i(TAG, " ************************ initDataUtils BEGIN *************************** ");
        AppCompatButton DataUtils0 = (AppCompatButton) findViewById(R.id.DataUtils0);
        AppCompatButton DataUtils1 = (AppCompatButton) findViewById(R.id.DataUtils1);
        AppCompatButton DataUtils2 = (AppCompatButton) findViewById(R.id.DataUtils2);

        DataUtils0.setText("隐藏手机号中间四位:" + DataUtils.hidePhone("15062255123"));
        DataUtils1.setText("格式化银行卡号(4位空格):" + DataUtils.formatBankCardNumber("6666669578937041"));


        Log.i(TAG, " ************************ initDataUtils END *************************** ");
    }

    private void initFileUtils() {
        Log.i(TAG, " ************************ initFileUtils BEGIN *************************** ");
        AppCompatButton FileUtils0 = (AppCompatButton) findViewById(R.id.FileUtils0);
        AppCompatButton FileUtils1 = (AppCompatButton) findViewById(R.id.FileUtils1);
        AppCompatButton FileUtils2 = (AppCompatButton) findViewById(R.id.FileUtils2);
        AppCompatButton FileUtils3 = (AppCompatButton) findViewById(R.id.FileUtils3);

        FileUtils0.setText("获取文件扩展名:" + FileUtils.getFileExt("text.apk"));

        Log.i(TAG, " ************************ initFileUtils END *************************** ");
    }

    private void initNotificationUtils() {

        Log.i(TAG, "判断通知功能是否开启 : " + NotificationUtils.isNotificationEnabled());
        Log.i(TAG, "判断通知渠道是否开启（8.0以上） : " + NotificationUtils.isNotificationChannelEnabled("LeakCanary Low Priority"));

    }

    private void initToastUtils() {
        ToastHelper.shortT("dfdfdfdf");
    }

    private void initLogUtils() {
        LogUtils.i("打印全局数据");
        LogUtils.iTag(TAG, "打印I数据");
        LogUtils.dTag(TAG, "打印D数据");
        LogUtils.eTag(TAG, "打印E数据");
        String xml = "<PROTOCOL><ERRORCODE>0</ERRORCODE><CLERK_SORT>0</CLERK_SORT><CLERK_NAME>院三星</CLERK_NAME><CLERK_PHONE>13511546546</CLERK_PHONE><CLERK_AREA>江苏省厅</CLERK_AREA><CLERK_REMARK>我市三星</CLERK_REMARK><ACCOUNT>qqq</ACCOUNT><URL>http://192.168.0.151:8888/</URL><UNIT></UNIT><DEPARTMENT_ID>149</DEPARTMENT_ID><PIC><LABEL>根</LABEL><LABEL>茎</LABEL><LABEL>叶</LABEL><LABEL>花</LABEL><LABEL>种子</LABEL><LABEL>整株</LABEL></PIC></PROTOCOL>\n";
        LogUtils.xml(TAG, xml);
        String json = "{\"title\":\"json在线解析（简版） -JSON在线解析\",\"json.url\":\"https://www.sojson.com/simple_json.html\",\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}";
        LogUtils.json(TAG, json);
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



    private void initRegexUtils() {
        Log.i(TAG, " ************************ RegexUtils BEGIN *************************** ");
        AppCompatButton RegexUtils0 = (AppCompatButton) findViewById(R.id.RegexUtils0);
        AppCompatButton RegexUtils1 = (AppCompatButton) findViewById(R.id.RegexUtils1);
        AppCompatButton RegexUtils2 = (AppCompatButton) findViewById(R.id.RegexUtils2);
        AppCompatButton RegexUtils3 = (AppCompatButton) findViewById(R.id.RegexUtils3);
        AppCompatButton RegexUtils4 = (AppCompatButton) findViewById(R.id.RegexUtils4);
        AppCompatButton RegexUtils5 = (AppCompatButton) findViewById(R.id.RegexUtils5);
        AppCompatButton RegexUtils6 = (AppCompatButton) findViewById(R.id.RegexUtils6);
        RegexUtils0.setText("验证手机号 15062255123: " + RegexUtils.isPhone("15062255123"));
        RegexUtils1.setText("验证邮箱 245794336@qq.com: " + RegexUtils.isEmail("245794336@qq.com"));
        RegexUtils2.setText("验证银行卡号 6666669578937041: " + RegexUtils.isBankCardNo("6666669578937041"));
        RegexUtils3.setText("验证身份证号 110101199003072092 : " + RegexUtils.isIDCardNo("110101199003072092"));
        RegexUtils4.setText("验证是否是url https://www.baidu.com : " + RegexUtils.isUrl("https://www.baidu.com"));

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