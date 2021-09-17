package cn.royalcms.starter.swagger.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Created with IntelliJ IDEA.
 * ClassName SwaggerAutoConfigure
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/9 18:46
 */
@EnableOpenApi
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
        AppSwaggerProperties.class
})
@Import({
        SwaggerConfiguration.class
})
public class SwaggerAutoConfigure {
}
