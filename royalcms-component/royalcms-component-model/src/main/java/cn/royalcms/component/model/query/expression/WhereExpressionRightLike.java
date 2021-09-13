package cn.royalcms.component.model.query.expression;

import cn.royalcms.component.model.query.WhereExpression;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class WhereExpressionRightLike implements WhereExpression {
    @Override
    public void build(List<Predicate> predicates, Expression expression, Object queryObject, CriteriaBuilder cb) {
        predicates.add(cb.like(expression, (Comparable) queryObject + "%"));
    }
}
