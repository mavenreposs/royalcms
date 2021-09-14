package cn.royalcms.facades.time;

import io.github.mavenreposs.php.functions.strtotime.StrToTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RC_TimeLong {

    private RC_TimeLong() {
        throw new UnsupportedOperationException();
    }

    public static TimeZone get_server_timezone() {
        return TimeZone.getDefault();
    }

    /**
     * 获得服务器的时区
     * eg: Asia/Tokyo
     * @return String
     */
    public static String server_timezone() {
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
        int num = tz.getOffset(Calendar.ZONE_OFFSET) / 1000;
        return -num;
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
    public static long gmtime() {
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
     *
     * @param str 格式化后的时间字符串
     * @return long
     */
    public static long gmstrtotime(String str) {
        long time = strtotime(str);
        if (time > 0) {
            time -= server_timezone_offset();
        }
        return time;
    }

    /**
     * 获得系统当前时间区的时间戳
     *
     * @return 10位时间戳
     */
    public static long time() {
        TimeZone tz = get_server_timezone();
        Calendar cal = Calendar.getInstance(tz);
        Date date = cal.getTime();
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获得JAVA的DATE时间转的时间戳
     * @param date Date
     * @return 10位时间戳
     */
    public static long time(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     *
     * @param str 格式化后的时间字符串
     * @return long
     */
    public static long strtotime(String str) {
        Date date = StrToTime.strtotime(str);
        assert date != null;
        long time = date.getTime();
        return (time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     *
     * @param str 格式化后的时间字符串
     * @param now int
     * @return long
     */
    public static long strtotime(String str, int now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     *
     * @param str 格式化后的时间字符串
     * @param now long
     * @return long
     */
    public static long strtotime(String str, long now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (time / 1000);
    }

    /**
     * 转换字符串形式的时间表达式为当前时区的时间戳
     *
     * @param str 格式化后的时间字符串
     * @param now Date
     * @return long
     */
    public static long strtotime(String str, Date now) {
        Date date = StrToTime.strtotime(str, now);
        assert date != null;
        long time = date.getTime();
        return (time / 1000);
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     *
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
     *
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
     *
     * @param format    时间格式
     * @param timestamp 指定时间戳
     * @return 格式化后的时间
     */
    public static String date(String format, long timestamp) {
        TimeZone tz = TimeZone.getDefault();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        Date date = new Date(timestamp * 1000L);
        return dateFormat.format(date);
    }

    /**
     * 获得当前时区的时间转换为自定义格式时间
     *
     * @param format 时间格式
     * @param date   指定日期
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
     *
     * @param format    时间格式
     * @param timestamp 指定时间戳
     * @return 格式化后的时间
     */
    public static String local_date(String format, long timestamp) {
        return date(format, timestamp);
    }

    /**
     * 返回当前系统时区的时间戳
     *
     * @return 10位秒级时间戳
     */
    public static long local_time() {
        return time();
    }

    /**
     * 将一个用户自定义时区的日期转为当前时区的时间戳
     *
     * @param str 日期字符串
     * @return long
     */
    public static long local_strtotime(String str) {
        return strtotime(str);
    }

    /**
     * 日期字符串 yyyy-MM-dd
     *
     * @param timestamp 时间戳
     * @return 日期字符串
     */
    public static String date_default_format(long timestamp) {
        return date("yyyy-MM-dd", timestamp);
    }

    /**
     * 时间字符串 HH:mm:ss
     *
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    public static String time_default_format(long timestamp) {
        return date("HH:mm:ss", timestamp);
    }

}
