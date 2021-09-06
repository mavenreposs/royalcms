package cn.royalcms.component.facades.array;

import cn.royalcms.component.facades.RC_Array;
import cn.royalcms.component.facades.RC_Log;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Test_Array {

    @Test
    public void testArrayReverse() {

        String[] a = {"php", "green", "red"};

        String[] b = RC_Array.array_reverse(a);

        RC_Log.info(Arrays.toString(b));

        Assertions.assertArrayEquals(new String[]{"red", "green", "php"}, b);

    }

}
