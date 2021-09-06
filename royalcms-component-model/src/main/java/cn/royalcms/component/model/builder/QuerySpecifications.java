package cn.royalcms.component.model.builder;


public class QuerySpecifications {

    public static <T> QueryPredicateBuilder<T> and() {
        return new QueryPredicateBuilder<T>(BooleanOperator.AND);
    }

    public static <T> QueryPredicateBuilder<T> or() {
        return new QueryPredicateBuilder<T>(BooleanOperator.OR);
    }

}
