package io.github.mavenreposs.royalcms.model.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

public interface WhereExpression {

    public void build(List<Predicate> predicates, Expression expression, Object queryObject, CriteriaBuilder cb);

}
