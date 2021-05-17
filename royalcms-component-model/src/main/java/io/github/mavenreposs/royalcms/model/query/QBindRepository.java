package io.github.mavenreposs.royalcms.model.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询绑定实体类
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindRepository {
    /**
     * 关联的DAO类名，xxx.class
     */
    Class<?> value();
}
