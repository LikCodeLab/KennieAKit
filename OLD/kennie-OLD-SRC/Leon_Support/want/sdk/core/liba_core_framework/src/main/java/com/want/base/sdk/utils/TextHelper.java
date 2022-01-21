package com.want.base.sdk.utils;

import android.text.SpannableStringBuilder;

/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/10/12<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class TextHelper {

    /**
     * 把一组{@link android.text.Spannable}加入到指定的{@link SpannableStringBuilder}中.
     *
     * @param builder
     * @param str
     * @param spans
     */
    public static void appendStyled(SpannableStringBuilder builder, String str, Object... spans) {
        builder.append(str);
        if (null != spans) {
            for (Object span : spans) {
                builder.setSpan(span, builder.length() - str.length(), builder.length(), 0);
            }
        }
    }

    /**
     * 把一组{@link android.text.Spannable}加入到指定的{@link SpannableStringBuilder}中,
     * 并且换行.
     *
     * @param builder
     * @param str
     * @param spans
     */
    public static void appendStyledLn(SpannableStringBuilder builder, String str, Object... spans) {
        appendStyled(builder, str, spans);
        builder.append("\n");
    }

    /**
     * 把一组{@link android.text.Spannable}加入到指定{@link SpannableStringBuilder}中的
     * 指定位置.
     *
     * @param builder
     * @param start
     * @param end
     * @param spans
     */
    public static void setStyle(SpannableStringBuilder builder,
                                int start,
                                int end,
                                Object... spans) {
        if (null != spans) {
            for (Object span : spans) {
                builder.setSpan(span, start, end, 0);
            }
        }
    }

}
