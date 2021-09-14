package cn.royalcms.component.model.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询绑定实体类
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindEntity {
    /**
     * 关联的实体类名，xxx.class
     * @return Class
     */
    Class<?> entityClass();
}
