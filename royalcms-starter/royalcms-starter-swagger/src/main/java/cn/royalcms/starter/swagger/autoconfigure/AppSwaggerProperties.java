package cn.royalcms.starter.swagger.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * ClassName AppSwaggerProperties
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/12 09:24
 */
@ConfigurationProperties(prefix = "app.swagger")
public class AppSwaggerProperties {

    /**
     * API package 路径定义
     */
    private String basePackage;

    /**
     * API Title
     */
    private String title = "接口文档";

    /**
     * API Description
     */
    private String description = "接口文档";

    /**
     * Api terms of service url.
     */
    private String termsOfServiceUrl = "#";

    /**
     * Api Version.
     */
    private String version = "1.0.0";

    /**
     * Api contact build info.
     */
    private Contact contact;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = buildCNString(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = buildCNString(description);
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    private static String buildCNString(String value) {
        return new String(value.getBytes(), StandardCharsets.UTF_8);
    }

    public static class Contact {
        /**
         * 联系人姓名
         */
        private String name;

        /**
         * 联系人主页URL
         */
        private String url;

        /**
         * 联系人邮箱
         */
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = buildCNString(name);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
}
