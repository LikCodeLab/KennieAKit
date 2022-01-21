package com.want.payment.wxpay;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.Locale;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/27/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
/*package*/ class Utils {
    public static final String RESULT_ACTION = "com.want.payment.ACTION_WXPAY";
    public static final String RESULT_WHAT = "com.want.payment.WHAT";
    public static final String RESULT_OBJ = "com.want.payment.OBJ";

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_CANCEL = -2;

    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_EXT_DATA = "ext_data";

    static void notifyPayResult(Context context, int code, String extData) {
        Intent intent = new Intent(RESULT_ACTION);
        intent.putExtra(RESULT_WHAT, code);
        intent.putExtra(RESULT_OBJ, extData);
        context.sendBroadcast(intent);
    }


    static String createExtData(String appId, String extData) {
        if (TextUtils.isEmpty(extData)) {
            extData = "";
        }
        return String.format(Locale.getDefault(),
                             KEY_APP_ID + "={%s};" + KEY_EXT_DATA + "={%s}",
                             appId,
                             extData);
    }

    static String getAppId(String extData) {
        return getValueByKey(extData, KEY_APP_ID);
    }

    static String getExtData(String extData) {
        return getValueByKey(extData, KEY_EXT_DATA);
    }

    private static String getValueByKey(String content, String key) {
        final String prefix = key + "={";
        for (String data : content.split(";")) {
            if (data.startsWith(prefix)) {
                return getValue(data, key);
            }
        }
        return "";
    }

    private static String getValue(String content, String key) {
        final String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(), content.lastIndexOf("}"));
    }
}
