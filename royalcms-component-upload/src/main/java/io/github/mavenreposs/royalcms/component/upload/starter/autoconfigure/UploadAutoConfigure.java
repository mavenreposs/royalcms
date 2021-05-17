package io.github.mavenreposs.royalcms.component.upload.starter.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
        AppUploadProperties.class,
})
public class UploadAutoConfigure {

}
