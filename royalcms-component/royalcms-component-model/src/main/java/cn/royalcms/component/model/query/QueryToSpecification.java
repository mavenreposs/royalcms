package cn.royalcms.component.model.query;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 自定义Query语言转Specification
 */
public class QueryToSpecification<T> implements Specification<T> {

    private BaseQuery query;

    public QueryToSpecification(BaseQuery query) {
        super();
        this.query = query;
    }

    /**
     *
     * @see Specification#toPredicate(Root, CriteriaQuery, CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cquery, CriteriaBuilder cb) {
        return BaseQueryPredicateBuilder.getPredicate(root, cb, cquery, this.query);
    }

}
