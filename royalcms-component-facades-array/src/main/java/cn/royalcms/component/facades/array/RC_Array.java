package cn.royalcms.component.facades.array;

import org.apache.commons.lang3.ArrayUtils;

public class RC_Array {

    private RC_Array() {
    }

    /**
     * 返回单元顺序相反的数组
     * @param array 字符串数组
     * @return 字符串数组
     */
    public static String[] array_reverse(String[] array) {
        ArrayUtils.reverse(array);
        return array;
    }



}
