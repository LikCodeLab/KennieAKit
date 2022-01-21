package cn.lukaslee.utils.support.date;


import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.lukaslee.utils.support.StringUtils;

/**
 * @author LukasLee
 * @time 2020/3/30 15:04
 * @classname DateUtils
 * description:日期工具类
 */
@SuppressWarnings("unused")
public class DateUtils {


    /**
     * 日期格式（yyyy-MM-dd）
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * 日期格式（HH:mm:ss）
     */
    public static final String FORMAT_TIME = "HH:mm:ss";
    /**
     * 日期格式（yyyy-MM-dd HH:mm:ss）
     */
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式（yyyy-MM-dd E）
     */
    public static final String FORMAT_DATE_WEEK = "yyyy-MM-dd E";
    /**
     * 日期格式（yyyy-MM-dd E HH:mm）
     */
    public static final String FORMAT_DATE_WEEK_TIME = "yyyy-MM-dd E HH:mm";


    /**
     * 英文简写如：2010
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";


    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";


    /**
     * 中文简写  如：2010年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2010年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";


    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前时间
     *
     * @param dateFormat 时间格式
     * @return
     */
    public static String getCurrentTime(String dateFormat) {
        return getTime(null, dateFormat);
    }

    /**
     * 获取时间
     *
     * @param milliseconds 时间戳
     * @param dateFormat   时间格式
     * @return
     */
    public static String getTime(String milliseconds, String dateFormat) {
        try {
            if (TextUtils.isEmpty(milliseconds)) {
                milliseconds = String.valueOf(new Date().getTime());
            }
            if (TextUtils.isEmpty(dateFormat)) {
                dateFormat = FORMAT_DATE_TIME;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.CHINESE);
            return simpleDateFormat.format(new Date(Long.parseLong(milliseconds)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取当前星期
     *
     * @return
     */
    public static String getCurrentWeek() {
        return getWeek(getCurrentTime(null));
    }

    /**
     * 获取星期
     *
     * @param date 日期
     * @return
     */
    public static String getWeek(String date) {
        String week = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE, Locale.CHINESE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            int weekKey = calendar.get(Calendar.DAY_OF_WEEK);
            switch (weekKey) {
                case 1:
                    week = "星期天";
                    break;
                case 2:
                    week = "星期一";
                    break;
                case 3:
                    week = "星期二";
                    break;
                case 4:
                    week = "星期三";
                    break;
                case 5:
                    week = "星期四";
                    break;
                case 6:
                    week = "星期五";
                    break;
                case 7:
                    week = "星期六";
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public String date2String(Date date, String pattern) {
        if (date == null) {
            return "";
        }

        if (TextUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param dateString
     * @param pattern
     * @return
     */
    private Date string2Date(String dateString, String pattern) {
        Date date = null;
        if (!TextUtils.isEmpty(dateString)) {
            if (TextUtils.isEmpty(pattern)) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            try {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 字符串转日期Calendar
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public Calendar string2Calendar(String dateString, String pattern) {
        Calendar calendar = null;
        try {
            if (!TextUtils.isEmpty(dateString)) {
                if (TextUtils.isEmpty(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Date d = sdf.parse(dateString);
                calendar = Calendar.getInstance();
                calendar.setTime(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * 将日期字符串转换成相应格式的日期字符串
     *
     * @param dateString
     * @param pattern1   pattern2
     * @return
     */
    public String string2Date2DateString(String dateString, String pattern1, String pattern2) {
        String dateString_temp = "";
        if (!TextUtils.isEmpty(dateString) && !TextUtils.isEmpty(pattern1)) {
            if (TextUtils.isEmpty(pattern2)) {
                pattern2 = "yyyy-MM-dd HH:mm:ss";
            }
            try {
                dateString_temp = date2String(string2Date(dateString, pattern1), pattern2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateString_temp;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param dateString
     * @param pattern
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private String getBeforeDay(String dateString, String pattern) {
        String dayBefore = "";
        if (!TextUtils.isEmpty(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (TextUtils.isEmpty(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm";
                }

                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                int day = c.get(Calendar.DATE);
                c.set(Calendar.DATE, day - 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private String getAfterDay(String dateString, String pattern) {
        String dayBefore = "";
        if (!TextUtils.isEmpty(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (TextUtils.isEmpty(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                int day = c.get(Calendar.DATE);
                c.set(Calendar.DATE, day + 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获得指定日期的1号
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public String getFirstOfMonth(String dateString, String pattern) {
        String dayBefore = "";
        if (!TextUtils.isEmpty(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (TextUtils.isEmpty(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                c.set(Calendar.DATE, 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获取当前日期的字符串格式
     *
     * @return
     */
    public String getCurrentDateString(String pattern) {
        return date2String(new Date(), pattern);
    }

    /**
     * 获取当前日期的前一天
     *
     * @return
     */
    public String getCurrentBeforeDay() {
        try {
            return getBeforeDay(date2String(new Date(), null), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前日期的后一天
     *
     * @return
     */
    public String getCurrentAfterDay() {
        try {
            return getAfterDay(date2String(new Date(), null), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * if true || d1 接近现在 || d1在d2的后边
     *
     * @param d1
     * @param d2
     * @return
     */
    public boolean compareDate(Date d1, Date d2) {
        boolean flag = false;
        if (d1.compareTo(d2) > 0) {
            flag = true;
        }
        return flag;
    }

    private static String getTodayZeroTime(long time, String formatS) {
        SimpleDateFormat format = new SimpleDateFormat(formatS);
        Date d1 = new Date(time);
        return format.format(d1);
    }

    public String getTodayZero(String format) {
        Date date = new Date();
        //每天的毫秒数
        long l = 24 * 60 * 60 * 1000;
        //date.getTime()是现在的毫秒数，它 减去 当天零点到现在的毫秒数（ 现在的毫秒数%一天总的毫秒数，取余。），理论上等于零点的毫秒数，不过这个毫秒数是UTC+0时区的。
        //减8个小时的毫秒值是为了解决时区的问题。
        String todayZeroTime = getTodayZeroTime(((date.getTime() - (date.getTime() % l) - 8 * 60 * 60 * 1000)), format);
        return todayZeroTime;
    }
}
