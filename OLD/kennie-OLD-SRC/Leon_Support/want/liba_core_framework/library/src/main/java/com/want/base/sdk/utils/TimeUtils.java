package com.want.base.sdk.utils;

import java.util.Calendar;
import java.util.Locale;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/6/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * A couple of util implements for time. These implements based on {@link Calendar}.
 * <br>
 */
@SuppressWarnings("unused")
public class TimeUtils {
    /** Times in millions of an hour */
    public static final long ONE_HOUR = 3600000;
    /** Times in millions of a day */
    public static final long ONE_DAY = ONE_HOUR * 24;

    /**
     * 指定的两个日期是否同一天
     *
     * @param c1 {@link Calendar}
     * @param c2 {@link Calendar}
     *
     * @return true, 相同；false，不同。
     */
    public static boolean isSameDay(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 是否同一周
     *
     * @param c1
     * @param c2
     *
     * @return
     */
    public static boolean isSameWeek(Calendar c1, Calendar c2) {
        return isSameYear(c1, c2)
                && c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 是否为同一个月
     *
     * @param c1
     * @param c2
     *
     * @return
     */
    public static boolean isSameMonth(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }

    /**
     * 是否为同一年
     *
     * @param c1
     * @param c2
     *
     * @return
     */
    public static boolean isSameYear(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    /**
     * 获取用于调试的时间信息
     *
     * @param timeInMillions
     *
     * @return
     */
    public static String getSimpleDateInfo(long timeInMillions) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(timeInMillions);
        return getSimpleDateInfo(calendar);
    }

    /**
     * 获取用于调试的时间信息
     *
     * @param calendar
     *
     * @return
     */
    public static String getSimpleDateInfo(Calendar calendar) {
        return String.format(Locale.getDefault(), "[%d/%d/%d-%d:%d:%d]",
                             calendar.get(Calendar.YEAR),
                             calendar.get(Calendar.MONTH) + 1,
                             calendar.get(Calendar.DAY_OF_MONTH),
                             calendar.get(Calendar.HOUR_OF_DAY),
                             calendar.get(Calendar.MINUTE),
                             calendar.get(Calendar.SECOND));
    }

    /**
     * c1是否在c2
     *
     * @param c1 {@link Calendar} 指定 Calendar
     * @param c2 {@link Calendar} 目标 Calendar
     *
     * @return true，之前；false，之后。
     */
    public static boolean isBefore(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)
                || c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)
                || c1.get(Calendar.DAY_OF_MONTH) < c2.get(Calendar.DAY_OF_MONTH)
                || c1.get(Calendar.HOUR_OF_DAY) < c2.get(Calendar.HOUR_OF_DAY)
                || c1.get(Calendar.MINUTE) < c2.get(Calendar.MINUTE)
                || c1.get(Calendar.SECOND) < c2.get(Calendar.SECOND);
    }

    /**
     * 获取两个日期间的间隔天数
     *
     * @param c1 {@link Calendar}
     * @param c2 {@link Calendar}
     *
     * @return 返回结果是负数，表示 c1在 c2之前；反之，c1在 c2之后。
     */
    public static long getInterval(Calendar c1, Calendar c2) {
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / ONE_DAY;
    }

    /**
     * 获取两个日期的间隔年数
     *
     * @param c1 {@link Calendar}
     * @param c2 {@link Calendar}
     *
     * @return 返回结果是负数，表示 c1在 c2之前；反之，c1在 c2之后。
     */
    public static int getIntervalYear(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
    }

    /**
     * 获取一周中的第一天
     *
     * @param thatDayInWeek
     * @param firstDayOfWeek
     *
     * @return
     */
    public static int getFirstDayInWeek(Calendar thatDayInWeek, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(thatDayInWeek.getTimeInMillis());

        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取一周中的最后一天
     *
     * @param thatDayInWeek  指定周的任意一天
     * @param firstDayOfWeek 周的第一天
     *
     * @return
     */
    public static int getEndDayInWeek(Calendar thatDayInWeek, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(thatDayInWeek.getTimeInMillis());

        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_WEEK, getEndDayOfWeekByFirstDay(firstDayOfWeek));

        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 根据一周开始的天获取这周结束的天
     *
     * @param firstDay 一周开始的天
     *
     * @return
     */
    public static int getEndDayOfWeekByFirstDay(int firstDay) {
        final int end = firstDay - 1;
        return end == 0 ? 7 : end;
    }


    /**
     * 获取一个月的第一天离周首日的间隔。
     * <p/>
     * 如，2015-05-01，周首日为周日，05-01当天是周五，此时间隔为5.
     *
     * @param thatDayInMonth 指定月份中的任意一天
     * @param firstDayOfWeek 周首日
     *
     * @return 指定月份第一天到周首日的间隔
     */
    public static int getIndexDayInWeekOfMonth(Calendar thatDayInMonth, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(thatDayInMonth.getTimeInMillis());
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek - firstDayOfWeek + 7) % 7;
    }

    /**
     * 获取一个月的最后一天离周末日的间隔
     * <p/>
     * 如，2015－05-31，周首日为周日，05-31当天是周日，此时离周末日的间隔是6.
     *
     * @param thatDayInMonth
     * @param firstDayOfWeek
     *
     * @return
     */
    public static int getLastDayInWeekOfMonth(Calendar thatDayInMonth, int firstDayOfWeek) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(thatDayInMonth.getTimeInMillis());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getEndDayInWeek(calendar, firstDayOfWeek) % 7;
    }

    /**
     * 将数字统一成两位数展示
     *
     * @param number
     *
     * @return
     */
    public static String getNumberInTwo(int number) {
        if (number < 10) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
}
