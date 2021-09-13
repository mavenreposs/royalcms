package cn.royalcms.component.model.query;


import cn.royalcms.facades.log.RC_Log;
import cn.royalcms.component.model.query.expression.*;

import javax.persistence.criteria.*;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * query转换builder类
 */
public class BaseQueryPredicateBuilder {

    private static final Map<String, WhereExpression> matchers;

    private static final Map<String, Join<?, ?>> fromJoinPools = new HashMap<>();

    static {
        matchers = new HashMap<>();
        matchers.put(Where.in.toString(), new WhereExpressionIn());
        matchers.put(Where.notIn.toString(), new WhereExpressionNotIn());
        matchers.put(Where.like.toString(), new WhereExpressionLike());
        matchers.put(Where.leftLike.toString(), new WhereExpressionLeftLike());
        matchers.put(Where.rightLike.toString(), new WhereExpressionRightLike());
        matchers.put(Where.equal.toString(), new WhereExpressionEqual());
        matchers.put(Where.notEqual.toString(), new WhereExpressionNotEqual());
        matchers.put(Where.lessThan.toString(), new WhereExpressionLessThan());
        matchers.put(Where.lessThanOrEqualTo.toString(), new WhereExpressionLessThanOrEqualTo());
        matchers.put(Where.greaterThan.toString(), new WhereExpressionGreaterThan());
        matchers.put(Where.greaterThanOrEqualTo.toString(), new WhereExpressionGreaterThanOrEqualTo());
        // add as many format as you want
    }

    // not thread-safe
    public static void registerMatcher(Where where, WhereExpression matcher) {
        matchers.put(where.toString(), matcher);
    }

    public static <T> Predicate getPredicate2(Root<T> root, CriteriaBuilder cb, BaseQuery query) {
        return getPredicate(root, cb, null, query);
    }

    public static <T> Predicate getPredicate(Root<T> root, CriteriaBuilder cb, CriteriaQuery<?> cquery,
                                             BaseQuery query) {

        List<Predicate> predicatesAnd = new ArrayList<Predicate>();
        try {
            Class<?> entityClass = queryEntity(query);
            if (entityClass == null) {
                // 是否返回NULL，待研究
                return null;
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(query.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Method readMethod = pd.getReadMethod();
                if ((pd.getName().indexOf("page") == 0) || (pd.getName().indexOf("sort") == 0) || (pd.getName().indexOf("clause") == 0)) {
                    continue;
                }

                if (!pd.getName().equals("class")) {
                    Object obj = readMethod.invoke(query);
                    if (obj != null) {
                        QBindAttrField fieldProp = (QBindAttrField) getBindFieldName(query, pd.getName());
                        assert fieldProp != null;
                        String bindAttrName = fieldProp.fieldName();
                        if (bindAttrName == null) {
                            // 查询字段名称默认等于属性名称
                            bindAttrName = pd.getName();
                        }

                        Expression expression;

                        String joinAttrName = fieldProp.joinName();
                        if (!joinAttrName.equals("")) {
                            String joinTypeName = fieldProp.joinType();
                            JoinType joinType;
                            if (joinTypeName.equals("left")) {
                                joinType = JoinType.LEFT;
                            } else if (joinTypeName.equals("right")) {
                                joinType = JoinType.RIGHT;
                            } else if (joinTypeName.equals("inner")) {
                                joinType = JoinType.INNER;
                            } else {
                                joinType = JoinType.LEFT;
                            }

                            String key = query.getClass().getName() + joinAttrName;
                            Join<T, T> fromJoin;
                            if (fromJoinPools.containsKey(key)) {
                                fromJoin = (Join<T, T>) fromJoinPools.get(key);
                            } else {
                                fromJoin = root.join(joinAttrName, joinType);
                                fromJoinPools.put(key, fromJoin);
                            }

                            expression = fromJoin.get(bindAttrName);
                        } else {
                            Path<?> from = root;
                            expression = from.get(bindAttrName);
                        }

                        WhereExpression whereExpression = matchers.get(fieldProp.where().toString());

                        whereExpression.build(predicatesAnd, expression, obj, cb);

                    }
                }

            }

            if (query.getClause() != null) {
                query.getClause().apply(root, cquery, cb, predicatesAnd);
            }

        } catch (Exception e) {
            RC_Log.object(e);
        }

        // 组合条件
        if (predicatesAnd.isEmpty()) {
            return cb.isTrue(cb.literal(true));
        }

        if (predicatesAnd.size() == 1) {
            return predicatesAnd.iterator().next();
        }
        return cb.and(predicatesAnd.toArray(new Predicate[predicatesAnd.size()]));
    }

    /**
     *
     * 获取查询实体类名称
     *
     * @author liuyi 2016年4月16日
     * @param query
     * @return
     */
    public static Class<?> queryEntity(BaseQuery query) {
        Annotation anno = query.getClass().getAnnotation(QBindEntity.class);
        if (anno != null)
            return ((QBindEntity) anno).entityClass();
        return null;
    }

    /**
     *
     * 获取绑定字段属性值
     *
     * @author liuyi 2016年4月16日
     * @param PropertyName
     * @return
     */
    public static Annotation getBindFieldName(BaseQuery query, String PropertyName) {
        try {
            Field field = query.getClass().getDeclaredField(PropertyName);
            Annotation anno = field.getAnnotation(QBindAttrField.class);
            if (anno != null) {
                return ((QBindAttrField) anno);
            }
        } catch (SecurityException e) {
            RC_Log.error("[BaseQueryPredicateBuilder.getBindAttrName SecurityException:]" + e.getMessage());
        } catch (NoSuchFieldException e) {
            RC_Log.error("[BaseQueryPredicateBuilder.getBindAttrName NoSuchFieldException:]" + e.getMessage());
        }
        return null;
    }


}
