package cn.royalcms.facades.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class RC_Random {

    private RC_Random() {
    }

    /**
     * Generate a more truly "random" alpha-numeric string.
     * 生成一个指定长度16的随机字符串
     *
     * @return String
     */
    public static String random() {
        return random(16);
    }

    /**
     * Generate a more truly "random" alpha-numeric string.
     * 生成一个指定长度的随机字符串
     *
     * @param length Random string length
     * @return String
     */
    public static String random(int length) {
        return RandomStringUtils.random(length, true, true).toLowerCase(Locale.ROOT);
    }



}
