package io.github.mavenreposs.component.bean.autoconfigure;

import cn.dscmall.component.bean.SpringBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({SpringBeanFactory.class})
public class BeanAutoConfigure {

}
