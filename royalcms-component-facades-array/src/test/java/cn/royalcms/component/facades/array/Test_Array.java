package cn.royalcms.component.facades.array;

import cn.royalcms.component.facades.log.RC_Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class Test_Array {

    @Test
    public void testArrayReverse() {

        String[] a = {"php", "green", "red"};

        String[] b = RC_Array.array_reverse(a);

        RC_Log.info(Arrays.toString(b));

        Assertions.assertArrayEquals(new String[]{"red", "green", "php"}, b);

    }

}
