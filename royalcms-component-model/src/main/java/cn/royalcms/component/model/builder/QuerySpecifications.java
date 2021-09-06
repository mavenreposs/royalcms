package cn.royalcms.component.model.builder;


import javax.persistence.criteria.Predicate;

public class QuerySpecifications {

    public static <T> QueryPredicateBuilder<T> and() {
        return new QueryPredicateBuilder<T>(Predicate.BooleanOperator.AND);
    }

    public static <T> QueryPredicateBuilder<T> or() {
        return new QueryPredicateBuilder<T>(Predicate.BooleanOperator.OR);
    }

}
