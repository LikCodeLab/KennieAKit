package com.kennie.example.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kennie.example.utils.R;
import com.kennie.utils.RegexUtils;
import com.kennie.utils.ScreenUtils;

public class MainExampleActivity extends AppCompatActivity {

    private static String TAG = MainExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_example);
        initUtils();
        initRegexUtils();
        initScreenUtils();
    }

    private void initUtils() {
        Log.i(TAG, " ************************ initUtils BEGIN *************************** ");
        Log.i(TAG, " ************************ initUtils END *************************** ");

    }


    private void initRegexUtils() {
        Log.i(TAG, " ************************ RegexUtils BEGIN *************************** ");

        Log.i(TAG, "验证是否是手机号 : " + RegexUtils.isMobile("15062255123"));
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