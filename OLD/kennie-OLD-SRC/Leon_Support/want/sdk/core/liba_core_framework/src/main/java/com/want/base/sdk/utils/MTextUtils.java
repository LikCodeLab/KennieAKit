package com.want.base.sdk.utils;

import android.os.Bundle;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/7/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
@SuppressWarnings("unused")
public class MTextUtils {

    private MTextUtils() {
        // hide
    }

    /**
     * Dump bundle to string. The formated strings likes below:
     * <pre>
     *     [key='key', value='value'], [key1='key1', value1='value1']
     * </pre>
     *
     * @param bundle {@link Bundle}
     *
     * @return Formated strings.
     */
    public static String toString(Bundle bundle) {
        if (null == bundle) {
            return "bundle is null!!";
        }

        StringBuilder builder = new StringBuilder();
        for (String key : bundle.keySet()) {
            builder.append("[key=");
            builder.append(key);
            builder.append(", value=");
            builder.append(bundle.get(key));
            builder.append("], ");
        }

        return builder.toString();
    }
}
