package cn.royalcms.facades.bean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * ClassName RCBeanTest
 * <p>
 * 2021/12/28 15:34
 *
 * @author Zhengdong Wang
 * @version jdk11
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
public class RCBeanTest {

    @DisplayName("Test getBean successful")
    @Test
    public void testBean() {
        System.out.println(RC_Bean.getBean(SpringBeanFactory.class));
    }

}
