package cn.royalcms.component.model.query.expression;

import cn.royalcms.component.model.query.WhereExpression;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 特殊处理
public class WhereExpressionIn implements WhereExpression {
    @Override
    public void build(List<Predicate> predicates, Expression expression, Object queryObject, CriteriaBuilder cb) {
//            if (pd.getPropertyType().getName().indexOf("List") > 0) {
        if (queryObject instanceof List) {
            List<?> value = (List<?>) queryObject;
            if (value.size() == 0) {
                // 防止生成LIST时，没有传入值，而查询条件会做全查处理，此处做特殊处理返回空条件
                ((List<?>) queryObject).add(null);
            }
            if (value.size() > 20) {
                Set<Object> set = new HashSet<>(value.size());
                // 如果in超过20个要去重处理
                set.addAll(value);
                value = new ArrayList<>(set);
            }
            predicates.add(expression.in(value));
        } else {
            List<?> tempList = List.of(queryObject);
            predicates.add(expression.in(tempList));
        }
    }
}
