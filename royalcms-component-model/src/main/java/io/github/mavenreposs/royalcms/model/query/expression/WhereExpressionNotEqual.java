package io.github.mavenreposs.royalcms.model.query.expression;

import io.github.mavenreposs.royalcms.model.query.WhereExpression;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class WhereExpressionNotEqual implements WhereExpression {
    @Override
    public void build(List<Predicate> predicates, Expression expression, Object queryObject, CriteriaBuilder cb) {
        predicates.add(cb.notEqual(expression, (Comparable) queryObject));
    }
}
