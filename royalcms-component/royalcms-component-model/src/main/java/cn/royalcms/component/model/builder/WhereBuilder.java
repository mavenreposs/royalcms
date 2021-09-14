package cn.royalcms.component.model.builder;

import cn.royalcms.component.model.query.WhereClauseInterface;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class WhereBuilder<T> {

    /**
     * 动态生成where语句
     * @param function WhereClauseInterface
     * @param <T> Specification
     * @return Specification
     */
    public static <T> Specification<T> getWhereClause(WhereClauseInterface function) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                function.apply(root, query, cb, predicate);
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public static <T> QueryPredicateBuilder<T> builder() {
        return new QueryPredicateBuilder<T>(Predicate.BooleanOperator.AND);
    }

    public static <T> QueryPredicateBuilder<T> builderOr() {
        return new QueryPredicateBuilder<T>(Predicate.BooleanOperator.OR);
    }

}
