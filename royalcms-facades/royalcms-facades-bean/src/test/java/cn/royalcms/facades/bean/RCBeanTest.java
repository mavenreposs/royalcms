package cn.royalcms.facades.bean;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName RCBeanTest
 * <p>
 * 2021/12/28 15:34
 *
 * @author Zhengdong Wang
 * @version jdk11
 */
@SpringBootTest(classes = TestApplication.class)
public class RCBeanTest {

    @Test
    public void testBean() {
        System.out.println(RC_Bean.getBean(SpringBeanFactory.class));
    }

}
