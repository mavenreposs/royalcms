package io.github.mavenreposs.royalcms.facades;

import io.github.mavenreposs.illuminate4j.support.CamelCaseUtil;
import io.github.mavenreposs.illuminate4j.support.Stringable;
import io.github.mavenreposs.illuminate4j.support.StudlyCache;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.mavenreposs.php.functions.PHPFunctions.*;


public class RC_String {

    private RC_String() {
    }

    /**
     * Get a new stringable object from the given string.
     * @param string 输入字符串
     * @return Stringable
     */
    public static Stringable of(String string) {
        return new Stringable(string);
    }

    /**
     * Return the remainder of a string after the first occurrence of a given value.
     * 方法返回字符串中指定值之后的所有内容。如果字符串中不存在这个值，它将返回整个字符串
     *
     * @param subject Subject
     * @param search Search
     */
    public static String after(String subject, String search) {
        if (search.equals("")) {
            return subject;
        }

        String[] subjects = explode(search, subject, 2);
        String[] subjects_reverse = RC_Array.array_reverse(subjects);

        return subjects_reverse[0];
    }

    /**
     * Return the remainder of a string after the last occurrence of a given value.
     * 方法返回字符串中指定值最后一次出现后的所有内容。如果字符串中不存在这个值，它将返回整个字符串
     *
     * @param subject Subject
     * @param search Search
     */
    public static String afterLast(String subject, String search) {
        if (search.equals("")) {
            return subject;
        }

        int position = strrpos(subject, search);

        if (position == -1) {
            return subject;
        }

        return StringUtils.substringAfterLast(subject, search);
    }

    /**
     * Determine if a given string is 7 bit ASCII.
     * 方法用于判断字符串是否是 7 位 ASCII
     *
     * @param value 输入字符串
     */
    public static boolean isAscii(char value) {
        return CharUtils.isAscii(value);
    }

    /**
     * Get the portion of a string before the first occurrence of a given value.
     * 方法返回字符串中指定值之前的所有内容
     *
     * @param subject 被搜索的内容
     * @param search 搜索的字符串
     */
    public static String before(String subject, String search) {
        if (search.equals("")) {
            return subject;
        }

        String[] subjects = explode(search, subject, 2);

        return subjects[0];
    }

    /**
     * Get the portion of a string before the last occurrence of a given value.
     * 方法返回字符串中指定值最后一次出现前的所有内容
     *
     * @param subject 被搜索的内容
     * @param search 搜索的字符串
     */
    public static String beforeLast(String subject, String search) {
        if (search.equals("")) {
            return subject;
        }

        int position = strrpos(subject, search);

        if (position == -1) {
            return subject;
        }

        return StringUtils.substringBeforeLast(subject, search);
    }

    /**
     * Convert a value to camel case.
     * 方法将指定字符串转换为 驼峰式 表示方法
     *
     * @param value 输入字符串
     */
    public static String camel(String value) {
        if (StudlyCache.camelCache.containsKey(value)) {
            return StudlyCache.camelCache.get(value);
        }

        String newValue = CamelCaseUtil.toCamelCase(value);

        StudlyCache.studlyCache.put(value, newValue);

        return newValue;
    }

    /**
     * Determine if a given string contains a given substring.
     * 方法判断指定字符串中是否包含另一指定字符串（区分大小写）
     *
     * @param haystack 输入字符串
     * @param needles 字符串
     */
    public static boolean contains(String haystack, String needles) {
        if (! needles.isEmpty() && strpos(haystack, needles) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Determine if a given string contains a given substring.
     * 方法判断指定字符串中是否包含另一指定字符串（区分大小写）
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串数组
     */
    public static boolean contains(String haystack, String[] needles) {
        for (String needle : needles)
        {
            if (! needle.isEmpty() && strpos(haystack, needle) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if a given string contains all array values.
     * 用于判断指定字符串是否包含指定数组中的所有值
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串数组
     */
    public static boolean containsAll(String haystack, String[] needles) {
        for (String needle : needles) {
            if (! contains(haystack, needles)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if a given string ends with a given substring.
     * 用于判断指定字符串是否以另一指定字符串结尾
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串
     */
    public static boolean endsWith(String haystack, String needles) {
        return StringUtils.endsWithAny(haystack, needles);
    }

    /**
     * Determine if a given string ends with a given substring.
     * 用于判断指定字符串是否以另一指定字符串结尾
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串
     */
    public static Boolean endsWith(String haystack, String[] needles) {
        return StringUtils.endsWithAny(haystack, needles);
    }

    /**
     * Cap a string with a single instance of a given value.
     * 将指定的字符串修改为以指定的值结尾的形式
     *
     * @param value 输入字符串
     * @param cap 修改字符串
     */
    public static String finish(String value, String cap) {
        return StringUtils.stripEnd(value, cap) + cap;
    }

    /**
     * Determine if a given string matches a given patern.
     * 方法用来判断字符串是否与指定模式匹配。星号 * 可用于表示通配符
     *
     * @param pattern 输入字符串
     * @param value 查找字符串
     */
    public static Boolean is(String pattern, String value) {
        return true;
    }

    /**
     * Determine if a given string is a valid UUID.
     * 用于判断指定字符串是否是有效的 UUID
     *
     * @param value 输入字符串
     */
    public static boolean isUuid(String value) {
        if (value.isEmpty()) {
            return false;
        }

        try {
            UUID uuid = UUID.fromString(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Convert a string to kebab case.
     * 将字符串转换为 烤串式（ kebab-case ） 表示方法
     *
     * @param value 输入字符串
     */
    public static String kebab(String value) {
        return snake(value, '-');
    }

    /**
     * Return the length of the given string.
     * 返回指定字符串的长度
     *
     * @param value 输入字符串
     */
    public static int length(String value) {
        return StringUtils.length(value);
    }

    /**
     * Limit the number of characters in a string.
     * 将字符串以指定长度进行截断
     *
     * @param value 输入字符串
     */
    public static String limit(String value) {
        return limit(value, 100, "...");
    }

    /**
     * Limit the number of characters in a string.
     * 将字符串以指定长度进行截断
     *
     * @param value 输入字符串
     * @param limit 切割长度
     */
    public static String limit(String value, int limit) {
        return limit(value, limit, "...");
    }

    /**
     * Limit the number of characters in a string.
     * 将字符串以指定长度进行截断
     *
     * @param value 输入字符串
     * @param limit 切割长度
     * @param end 尾部填充
     */
    public static String limit(String value, int limit, String end) {
        if (value.length() <= limit) {
            return value;
        }

        return StringUtils.trim(StringUtils.substring(value, 0, limit)) + end;
    }

    /**
     * Convert the given string to lower-case.
     * 用于将字符串转换为小写
     *
     * @param value 输入字符串
     */
    public static String lower(String value) {
        return StringUtils.toRootLowerCase(value);
    }

    /**
     * Limit the number of words in a string.
     * @param value 输入字符串
     * @param words 截图多少个单词
     * @param end 追加后缀字符串
     */
    public static String words(String value, Integer words, String end) {
        String pattern = "^\\s*+(?:\\S++\\s*+){1," + words + "}";
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(value);
        if (m.find()) {
            value = m.group(0);
        }
        return rtrim(value) + end;
    }

    /**
     * Generate a more truly "random" alpha-numeric string.
     * 生成一个指定长度16的随机字符串
     */
    public static String random() {
        return random(16);
    }

    /**
     * Generate a more truly "random" alpha-numeric string.
     * 生成一个指定长度的随机字符串
     *
     * @param length Random string length
     */
    public static String random(Integer length) {
        return RandomStringUtils.random(length, true, true).toLowerCase(Locale.ROOT);
    }

    /**
     * Replace a given value in the string sequentially with an string.
     * 使用数组顺序替换字符串中的给定值
     *
     * @param search 需要替换的字符串
     * @param replace 替换后的内容
     * @param subject 输入内容
     */
    public static String replace(String search, String replace, String subject) {
        return StringUtils.replace(subject, search, replace);
    }

    /**
     * Replace a given value in the string sequentially with an array.
     * 使用数组顺序替换字符串中的给定值
     *
     * @param search 需要替换的字符串
     * @param replace 替换后的内容
     * @param subject 输入内容
     */
    public static String replaceArray(String search, String[] replace, String subject) {
        subject = StringUtils.replace(subject, search, "%s");
        return String.format(subject, replace);
    }

    /**
     * Replace the first occurence of a given value in the string.
     * 替换字符串中给定值的第一个匹配项
     *
     * @param search 需要替换的字符串
     * @param replace 替换后的内容
     * @param subject 输入内容
     */
    public static String replaceFirst(String search, String replace, String subject) {
        return subject.replaceFirst(search, replace);
    }

    /**
     * Replace the last occurence of a given value in the string.
     * 替换字符串中最后一次出现的给定值
     *
     * @param search 需要替换的字符串
     * @param replace 替换后的内容
     * @param subject 输入内容
     */
    public static String replaceLast(String search, String replace, String subject) {
        subject = StringUtils.reverse(subject);
        subject = subject.replaceFirst(StringUtils.reverse(search), StringUtils.reverse(replace));
        subject = StringUtils.reverse(subject);
        return subject;
    }

    /**
     * Begin a string with a single instance of a given value.
     * @param value 输入字符串
     * @param prefix 前缀
     */
    public static String start(String value, String prefix) {
        return prefix + StringUtils.stripStart(value, prefix);
    }

    /**
     * convert the given string to upper-case.
     * 用于将指定字符串转换为大写
     *
     * @param value 输入字符串
     */
    public static String upper(String value) {
        return StringUtils.toRootUpperCase(value);
    }

    /**
     * convert the given string to title case.
     * 把指定的字符串，每个单词首字母大写
     *
     * @param value 输入字符串
     */
    public static String title(String value) {
        return WordUtils.capitalizeFully(value);
    }

    /**
     * Generate a URL friendly "slug" from a given string.
     * @param title
     * @param separator
     * @param language
     */
    public static void slug(String title, String separator, String language) {

    }

    /**
     * Convert a string to snake case.
     * @param value 输入字符串
     */
    public static String snake(String value) {
        return snake(value, '_');
    }

    /**
     * Convert a string to snake case.
     * @param value 输入字符串
     * @param delimiter 分割符
     */
    public static String snake(String value, char delimiter) {
        String key = new String(value) + delimiter;

        if (StudlyCache.snakeCache.containsKey(key)) {
            return StudlyCache.snakeCache.get(key);
        }

        String newValue = CamelCaseUtil.snake(value, delimiter);

        StudlyCache.snakeCache.put(value, newValue);

        return newValue;
    }

    /**
     * Determine if a given string starts with a given substring.
     * 判断第二个参数是否是第一个参数的开头返回 true or false
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串
     */
    public static Boolean startsWith(String haystack, String needles) {
        return StringUtils.startsWithAny(haystack, needles);
    }

    /**
     * Determine if a given string starts with a given substring.
     * 判断第二个参数是否是第一个参数的开头返回 true or false
     *
     * @param haystack 输入字符串
     * @param needles 查找字符串
     */
    public static Boolean startsWith(String haystack, String[] needles) {
        return StringUtils.startsWithAny(haystack, needles);
    }

    /**
     * Convert a value to studly caps case.
     * 方法将带有 _的字符串转换成驼峰命名的字符串，与 Str::snake() 相反
     * @param value 字符串
     */
    public static String studly(String value) {
        String key = new String(value);

        if (StudlyCache.studlyCache.containsKey(key)) {
            return StudlyCache.studlyCache.get(key);
        }

        String newValue = CamelCaseUtil.toCapitalizeCamelCase(value);

        StudlyCache.studlyCache.put(key, newValue);

        return newValue;
    }

    /**
     * Returns the portion of string specified by the start and length parameters.
     * @param string
     * @param start
     * @param length
     */
    public static String substr(String string, Integer start, Integer length) {
        return string;
    }

    /**
     * Make a string's first character uppercase.
     * 把指定的字符串首字母大写
     *
     * @param string 输入字符串
     */
    public static String ucfirst(String string) {
        return ucwords(string);
    }

    /**
     * Generate a UUID (version 4).
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a time-ordered UUID (version 4).
     * 用于生成一个「时间戳优先」的 UUID ，它可作为数据库索引列的有效值
     */
    public static String orderedUuid() {
        return uuid();
    }

    /**
     * Set the callable that will be used to generate UUIDs.
     * @param factory
     */
    public static void createUuidsUsing(String factory) {

    }

    /**
     * Indicate that UUIDs should be created normally and not using a custom factory.
     */
    public static void createUuidsNormally() {

    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的两侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     */
    public static String padBoth(String value, int length) {
        return padBoth(value, length, " ");
    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的两侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     * @param pad 填充字符串
     */
    public static String padBoth(String value, int length, String pad) {
        return StringUtils.center(value, length, pad);
    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的左侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     */
    public static String padLeft(String value, int length) {
        return padLeft(value, length, " ");
    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的左侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     * @param pad 填充字符串
     */
    public static String padLeft(String value, int length, String pad) {
        return StringUtils.leftPad(value, length, pad);
    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的右侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     */
    public static String padRight(String value, int length) {
        return padRight(value, length, " ");
    }

    /**
     * Pad both sides of a string with another.
     * 包装了 PHP 的 str_pad 函数，在指定字符串的右侧填充上另一字符串
     *
     * @param value 输入字符串
     * @param length 长度
     * @param pad 填充字符串
     */
    public static String padRight(String value, int length, String pad) {
        return StringUtils.rightPad(value, length, pad);
    }

    /**
     * Get the portion of a string between two given values.
     * @param subject 输入字符串
     * @param from 开始字符
     * @param to 结束字符
     * @return 返回字符串
     */
    public static String between(String subject, String from, String to) {
        if (from.equals("") || to.equals("")) {
            return subject;
        }

        return StringUtils.substringBetween(subject, from, to);
    }





}
