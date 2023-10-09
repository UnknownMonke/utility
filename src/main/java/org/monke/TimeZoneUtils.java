package org.monke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneUtils {

    /** Gets the current localized date. */
    public static Date getLocalizedDate(TimeZone timeZone, Locale locale) {
        Calendar c = Calendar.getInstance(timeZone, locale);
        return c.getTime();
    }

    /**
     * Returns a date formatted to the given format (ex: dd/MM/yyyy) with the given timezone.
     * @return Date.
     */
    public static String formatDate(Date date, String pattern, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * Returns a date formatted to the given format (ex: dd/MM/yyyy) with the given timezone.
     * @return String.
     */
    public static Date parseDate(String dateStr, String pattern, TimeZone timeZone) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(timeZone);
        return formatter.parse(dateStr);
    }

    /** Returns the current date as a Calendar, with only year, month and day. */
    public static Calendar getDateWithoutTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    /** Returns the date 30 days before the current date. */
    public static Calendar getDateThirtyDaysBeforeCurrent() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date());
        c2.setTime(new Date());
        c1.roll(Calendar.DAY_OF_YEAR, -30);
        if(c1.after(c2)) {
            c1.roll(Calendar.YEAR, -1);
        }
        return c1;
    }

    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Calendar getToday() {
        return getDateWithoutTime(new Date());
    }

    public static Calendar getTomorrow() {
        Calendar c = getDateWithoutTime(new Date());
        c.roll(Calendar.DAY_OF_MONTH, 1);
        return c;
    }

    public static Calendar getEndOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar;
    }

    public static Integer findMonthToInt(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Integer[] findMonthYearToInt(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new Integer[]{cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)};
    }
}
