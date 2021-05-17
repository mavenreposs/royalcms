package io.github.mavenreposs.royalcms.component.facades;

import mavenreposs.php.functions.strtotime.StrToTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RC_Time {

    private RC_Time() {
        throw new UnsupportedOperationException();
    }

    private static String timeZone = "PRC";

    public static void setTimeZoneString(String zone) {
        timeZone = zone;
    }

    public static void setTimeZoneInteger(Integer zone) {
        String tz;
        if (zone > 0) {
            tz = "Etc/GMT-" + zone.toString();
        }
        else if (zone < 0) {
            tz = "Etc/GMT+" + Integer.valueOf(Math.abs(zone)).toString();
        }
        else {
            tz = "Etc/GMT";
        }

        System.out.println(zone.toString());

        timeZone = tz;
    }

    public static String getTimeZoneString() {
        return timeZone;
    }

    public static void set_server_timezone() {
        TimeZone zone = TimeZone.getTimeZone(getTimeZoneString());
        TimeZone.setDefault(zone);
    }

    public static TimeZone get_server_timezone() {
        return TimeZone.getDefault();
    }

    /**
     * 获得服务器的时区
     * eg: Asia/Tokyo
     */
    public static String server_timezone()
    {
        ZoneId defaultZone = ZoneId.systemDefault();
        return defaultZone.getId();
    }

    /**
     * 获得服务器的时区偏移小时
     * @return 偏移秒
     */
    public static int server_timezone_hour()
    {
        int offset = server_timezone_offset();
        return offset / 3600;
    }

    /**
     * 获得服务器的时区偏移量
     * @return 偏移秒
     */
    public static int server_timezone_offset()
    {
        TimeZone tz = TimeZone.getDefault();
        return tz.getOffset(Calendar.ZONE_OFFSET) / 1000;
    }

    /**
     * 获取格林威治零时区的时区
     * @return TimeZone
     */
    public static TimeZone gmtimezone() {
        return TimeZone.getTimeZone("GMT");
    }

    /**
     * 获得当前格林威治时间的时间戳
     * @return 10位时间戳
     */
    public static int gmtime() {
        TimeZone tz = gmtimezone();
        Calendar cal = Calendar.getInstance(tz);
        Date date = cal.getTime();
        int time = (int) (date.getTime() / 1000);
        time = time - server_timezone_offset();
        return time;
    }

    /**
     * 获得当前格林威治时间的自定义格式时间
     * @param format 时间格式
     * @return 格式化后的时间
     */
    public static String gmdate(String format) {
        TimeZone tz = gmtimezone();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(new Date());
    }

    /**
     * 转换字符串形式的时间表达式为GMT时间戳
     * @param str 格式化后的时间字符串
     */
    public static int gmstrtotime(String str) {
        int time = strtotime(str);
        if (time > 0) {
            time -= server_timezone_offset();
        }
        return time;
    }

    /**
     * 获得系统当前时间区的时间戳
     * @return 10位时间戳
     */
    public static int time() {
        TimeZone tz = get_server_timezone();
        Calendar cal = Calendar.getInstance(tz);
        Date date = cal.getTime();
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获得JAVA的DATE时间转的时间戳
     *
     * @return 10位时间戳
     */
    public static int time(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     * @param str 格式化后的时间字符串
     */
    public static int strtotime(String str) {
        Date date = StrToTime.strtotime(str);
        assert date != null;
        long time = date.getTime();
        return (int)(time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     * @param str 格式化后的时间字符串
     */
    public static int strtotime(String str, int now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (int)(time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     * @param str 格式化后的时间字符串
     */
    public static int strtotime(String str, long now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (int)(time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     * @param str 格式化后的时间字符串
     */
    public static int strtotime(String str, Date now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (int)(time / 1000);
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     * @return 格式化后的时间
     */
    public static String date() {
        String format = "yyyy-MM-dd HH:mm:ss";
        TimeZone tz = TimeZone.getDefault();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(new Date());
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     * @param format 时间格式
     * @return 格式化后的时间
     */
    public static String date(String format) {
        TimeZone tz = TimeZone.getDefault();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(new Date());
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     * @param format 时间格式
     * @param timestamp 指定时间戳
     * @return 格式化后的时间
     */
    public static String date(String format, int timestamp) {
        TimeZone tz = TimeZone.getDefault();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        Date date = new Date(timestamp * 1000L);
        return dateFormat.format(date);
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     * @param format 时间格式
     * @param date 指定日期
     * @return 格式化后的时间
     */
    public static String date(String format, Date date) {
        TimeZone tz = TimeZone.getDefault();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(date);
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     * @param format 时间格式
     * @param timestamp 指定时间戳
     * @return 格式化后的时间
     */
    public static String local_date(String format, int timestamp) {
        return date(format, timestamp);
    }

    /**
     * 返回当前系统时区的时间戳
     * @return 10位秒级时间戳
     */
    public static int local_time() {
        return time();
    }

    /**
     * 将一个用户自定义时区的日期转为当前时区的时间戳
     * @param str 日期字符串
     */
    public static int local_strtotime(String str) {
        return strtotime(str);
    }

    /**
     * 日期字符串 yyyy-MM-dd
     * @param timestamp 时间戳
     * @return 日期字符串
     */
    public static String date_default_format(int timestamp) {
        return date("yyyy-MM-dd", timestamp);
    }

    /**
     * 时间字符串 HH:mm:ss
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    public static String time_default_format(int timestamp) {
        return date("HH:mm:ss", timestamp);
    }

}
