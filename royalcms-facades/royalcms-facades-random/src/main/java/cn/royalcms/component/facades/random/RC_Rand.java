package cn.royalcms.component.facades.random;

import org.apache.commons.lang3.RandomUtils;

/**
 * 产生一个随机整数
 */
public class RC_Rand {

    private RC_Rand() {
    }

    /**
     * 产生一个随机整数
     * @param min 返回的最低值（默认：0）
     * @param max 返回的最高值
     * @return 随机整数
     */
    public static Integer random(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    /**
     * 产生一个随机整数
     * @param max 返回的最高值
     * @return 随机整数
     */
    public static Integer random(int max) {
        return RandomUtils.nextInt(0, max);
    }

    /**
     * 产生一个随机整数
     * @return 随机整数
     */
    public static Integer random() {
        return RandomUtils.nextInt();
    }



}
