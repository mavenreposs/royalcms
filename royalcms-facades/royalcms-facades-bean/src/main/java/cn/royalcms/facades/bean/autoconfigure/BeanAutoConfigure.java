package cn.royalcms.facades.bean.autoconfigure;

import cn.royalcms.facades.bean.SpringBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({SpringBeanFactory.class})
public class BeanAutoConfigure {

}
