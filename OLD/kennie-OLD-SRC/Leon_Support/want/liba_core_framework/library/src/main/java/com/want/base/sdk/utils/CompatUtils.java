package com.want.base.sdk.utils;

import android.os.Build;

import static android.os.Build.VERSION_CODES.DONUT;
import static android.os.Build.VERSION_CODES.ECLAIR_MR1;
import static android.os.Build.VERSION_CODES.GINGERBREAD;
import static android.os.Build.VERSION_CODES.HONEYCOMB;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;
import static android.os.Build.VERSION_CODES.KITKAT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.M;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 8/11/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class CompatUtils {

    private static final int SDK_INIT;

    static {
        SDK_INIT = Build.VERSION.SDK_INT;
    }

    private CompatUtils() {
        //no instance
    }

    public static boolean has4() {
        return SDK_INIT >= DONUT;
    }

    public static boolean has7() {
        return SDK_INIT >= ECLAIR_MR1;
    }

    public static boolean has9() {
        return SDK_INIT >= GINGERBREAD;
    }


    public static boolean has11() {
        return SDK_INIT >= HONEYCOMB;
    }

    public static boolean has14() {
        return SDK_INIT >= ICE_CREAM_SANDWICH;
    }

    public static boolean has16() {
        return SDK_INIT >= JELLY_BEAN;
    }


    public static boolean has17() {
        return SDK_INIT >= JELLY_BEAN_MR1;
    }

    public static boolean has18() {
        return SDK_INIT >= JELLY_BEAN_MR2;
    }

    public static boolean has19() {
        return SDK_INIT >= KITKAT;
    }

    public static boolean has21() {
        return SDK_INIT >= LOLLIPOP;
    }

    public static boolean has23() {
        return SDK_INIT >= M;
    }

}
