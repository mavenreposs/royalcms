package io.github.mavenreposs.royalcms.model.query;

/**
 * 目前系统支持的where查询条件关键字
 */
public enum Where {
    in,
    notIn,
    like, // %_%
    leftLike, // %_
    rightLike, // _%
    equal, // =
    notEqual, // <>
    lessThan, // <
    lessThanOrEqualTo, // <=
    greaterThan, // >
    greaterThanOrEqualTo // >=
}
