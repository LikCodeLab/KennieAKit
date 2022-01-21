package cn.lukas.utils.core.old;

import android.content.Context;

/**
 * 密度转换工具类
 * @author Andy
 * https://github.com/lijiamu/EasyUtil_Android
 */
public class DensityUtil {
    /**
     * dip转像素
     * @param context Context对象
     * @param dip dip值
     * @return 像素值
     */
    public static int dipToPixels(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dip * scale + 0.5f);
    }

    /**
     * 像素转dip
     * @param context Context对象
     * @param pixels  像素
     * @return dip值
     */
    public static float pixelsToDip(Context context, int pixels) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pixels / scale;
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue 像素
     * @param fontScale x
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp值
     * @return   像素值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
