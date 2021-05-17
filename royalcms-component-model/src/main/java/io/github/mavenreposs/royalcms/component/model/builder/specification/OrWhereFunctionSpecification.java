package io.github.mavenreposs.royalcms.component.model.builder.specification;


import io.github.mavenreposs.royalcms.component.model.builder.QueryPredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class OrWhereFunctionSpecification<T> extends AbstractSpecification<T> {

    private final Function<QueryPredicateBuilder<T>, QueryPredicateBuilder<T>> function;

    public OrWhereFunctionSpecification(Function<QueryPredicateBuilder<T>, QueryPredicateBuilder<T>> function) {
        this.function = function;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        QueryPredicateBuilder<T> builder = new QueryPredicateBuilder<>(BooleanOperator.OR);
        builder = function.apply(builder);
        Predicate[] predicates = getPredicates(root, query, cb, builder);
        return cb.and(cb.or(predicates));
    }

    private Predicate[] getPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, QueryPredicateBuilder<T> builder) {
        List<Specification<T>> specifications = builder.getSpecifications();
        Predicate[] predicates = new Predicate[specifications.size()];
        for (int i = 0; i < specifications.size(); i++) {
            predicates[i] = specifications.get(i).toPredicate(root, query, cb);
        }
        if (Objects.equals(predicates.length, 0)) {
            return null;
        }
        return predicates;
    }

}
