package cn.royalcms.component.bean.autoconfigure;

import cn.royalcms.component.bean.SpringBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({SpringBeanFactory.class})
public class BeanAutoConfigure {

}
