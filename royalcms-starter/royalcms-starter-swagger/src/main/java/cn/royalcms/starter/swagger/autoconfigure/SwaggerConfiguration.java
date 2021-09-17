package cn.royalcms.starter.swagger.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * Swagger API文档配置
 *
 * Created with IntelliJ IDEA.
 * ClassName SwaggerConfiguration
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/9 18:42
 */
@Configuration(proxyBeanMethods = false)
public class SwaggerConfiguration {

    @Autowired
    private AppSwaggerProperties appSwaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .protocols(newHashSet("https", "http"))// 支持的通讯协议集合
                .consumes(getAllConsumeContentTypes())
                .produces(getAllProduceContentTypes())
                .select()
                .apis(RequestHandlerSelectors.basePackage(appSwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalRequestParameters(getGlobalRequestParameters()) // Adds default parameters which will be applied to all operations.;
                .globalResponses(HttpMethod.GET, getGlobalResonseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResonseMessage());
    }

    private Set<String> newHashSet(String... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<String>(Arrays.asList(ts));
        }
        return null;
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("X-Token")
                .description("调用接口凭证 PS: 79218f8e710fc755a222de45f43c3fd969b87972")
                .required(true)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build());
        return parameters;
    }

    private Set<String> getAllConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        // Add other media types if required in future
        consumes.add(MediaType.APPLICATION_JSON_VALUE);
        consumes.add(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        return consumes;
    }

    private Set<String> getAllProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        // Add other media types if required in future
        produces.add(MediaType.APPLICATION_JSON_VALUE);
        return produces;
    }

    //生成通用响应信息
    private List<Response> getGlobalResonseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("400").description("资源错误").build());
        responseList.add(new ResponseBuilder().code("401").description("Unauthorized").build());
        responseList.add(new ResponseBuilder().code("403").description("Forbidden").build());
        responseList.add(new ResponseBuilder().code("404").description("Not Found").build());
        responseList.add(new ResponseBuilder().code("500").description("服务器错误").build());
        return responseList;
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     * 预览地址:swagger-ui.html
     *
     * @return ApiInfoBuilder
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appSwaggerProperties.getTitle())
                .termsOfServiceUrl(appSwaggerProperties.getTermsOfServiceUrl())
                .description(appSwaggerProperties.getDescription())
                .version(appSwaggerProperties.getVersion())
                .contact(new Contact(appSwaggerProperties.getContact().getName(), appSwaggerProperties.getContact().getUrl(), appSwaggerProperties.getContact().getEmail()))
                .build();
    }
}
