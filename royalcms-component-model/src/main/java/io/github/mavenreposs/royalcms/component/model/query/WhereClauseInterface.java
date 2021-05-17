package io.github.mavenreposs.royalcms.component.model.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@FunctionalInterface
public interface WhereClauseInterface<T>  {

    void apply(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicate);

}
