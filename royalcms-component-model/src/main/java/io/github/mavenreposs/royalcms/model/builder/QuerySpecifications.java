package io.github.mavenreposs.royalcms.model.builder;


import static javax.persistence.criteria.Predicate.BooleanOperator.AND;
import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

public class QuerySpecifications {

    public static <T> QueryPredicateBuilder<T> and() {
        return new QueryPredicateBuilder<T>(BooleanOperator.AND);
    }

    public static <T> QueryPredicateBuilder<T> or() {
        return new QueryPredicateBuilder<T>(BooleanOperator.OR);
    }

}
