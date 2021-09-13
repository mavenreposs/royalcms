package cn.royalcms.component.model.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询绑定属性
 */

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindAttrField {

    /**
     * 字段名
     */
    String fieldName();

    /**
     * 查询条件
     */
    Where where();

    /**
     * 连表查询，模型中关联字段名
     */
    String joinName() default "";

    /**
     * Join类型，left, right, inner 可选，默认为left
     */
    String joinType() default "";

}
