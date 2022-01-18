package com.kennie.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.kennie.utils.manager.UtilsManager;

import java.lang.reflect.Method;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：ScreenUtils
 * Date：2021/12/12 23:15
 * Desc：手机屏幕管理类
 *
 * <p>
 * --获取状态栏高度px                                      {@link #getStatusBarHeight()}
 * --获取ActionBar高度px                                         {@link #getActionBarHeight()}
 * --获取导航栏高度px                                         {@link #getNavigationBarHeight()}
 * --获取屏幕宽度                                         {@link #getScreenWidth()}
 * --获取屏幕高度                                         {@link #getScreenHeight()}
 * --获取屏幕密度                                         {@link #getScreenDensity()}
 * --dp转换为px                                         {@link #dp2px(float dp)}
 * --sp转换为px                                         {@link #sp2px(float sp)}
 * --px转换为dp                                         {@link #px2dp(int px)}
 * </p>
 */
public abstract class ScreenUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * 初始化
     *
     * @param ctx 上下文对象
     */
    public static void init(Context ctx) {
        context = ctx;
    }

    /**
     * 获得屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) UtilsManager.get().getAppContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth1() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获得屏幕高度(不包含虚拟功能键)
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * 获取屏幕高度(不包含虚拟功能键)
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight1() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取屏幕密度
     *
     * @return
     */
    public static float getScreenDensity() {
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * 获取状态栏高度px
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int statusHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
    }


    /**
     * 获取ActionBar高度px
     *
     * @return ActionBar高度
     */
    public static int getActionBarHeight() {
        int height = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            height = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return height;
    }


    /**
     * 获取导航栏高度
     *
     * @return 导航栏高度 px
     */
    public static int getNavigationBarHeight() {
        String field = "";
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            field = "navigation_bar_height";
        } else {
            field = "navigation_bar_height_landscape";
        }
        int resourceId = context.getResources().getIdentifier(field, "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 获取虚拟按键的高度
     *
     * @return 虚拟按键的高度
     */
    public static int getNavigationBarHeight1() {
        int totalHeight = getDpi();
        int contentHeight = getScreenHeight();
        return totalHeight - contentHeight;
    }

    /**
     * 获取屏幕原始尺寸高度(包括虚拟功能键高度)
     *
     * @return 尺寸高度
     */
    public static int getDpi() {
        int dpi;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            dpi = getScreenHeight();
        }
        return dpi;
    }


    /**
     * 判断设备是否有虚拟键
     *
     * @return true 有 false 没有
     */
    public static boolean isVirtualKey() {
        return getScreenHeight() != getDpi();
    }


    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return dp2px(dp, TypedValue.COMPLEX_UNIT_DIP);
    }


    /**
     * sp转换为px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        return dp2px(sp, TypedValue.COMPLEX_UNIT_SP);
    }


    /**
     * px转换为dp
     */
    public static float px2dp(int px) {
        return px / getScreenDensity();
    }

    private static int dp2px(float value, int unit) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(unit, value, displayMetrics);
    }


}
