package io.github.mavenreposs.royalcms.component.model.builder;

import cn.dscmall.component.model.builder.specification.*;
import io.github.mavenreposs.component.model.builder.specification.*;
import io.github.mavenreposs.model.builder.specification.*;
import io.github.mavenreposs.royalcms.component.model.builder.specification.*;
import io.github.mavenreposs.royalcms.model.builder.specification.*;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class QueryPredicateBuilder<T> {

    private final Predicate.BooleanOperator operator;

    private List<Specification<T>> specifications;

    public QueryPredicateBuilder(Predicate.BooleanOperator operator) {
        this.operator = operator;
        this.specifications = new ArrayList<>();
    }

    public QueryPredicateBuilder<T> where(String property, Object... values) {
        return eq(true, property, values);
    }

    public QueryPredicateBuilder<T> orWhere(String property, Object... values) {
        return this.predicate(true, new OrWhereSpecification(property, values));
    }

    public QueryPredicateBuilder<T> orWhere(Function<QueryPredicateBuilder<T>, QueryPredicateBuilder<T>> function) {
        return this.predicate(true, new OrWhereFunctionSpecification(function));
    }

    public QueryPredicateBuilder<T> whereBetween(String property, Range<? extends Comparable<?>> range) {
        return between(true, property, range);
    }

    public QueryPredicateBuilder<T> whereBetween(String property, int start, int end) {
        return between(true, property, Range.of(Range.Bound.exclusive(start), Range.Bound.exclusive(end)));
    }

    public QueryPredicateBuilder<T> whereBetween(String property, long start, long end) {
        return between(true, property, Range.of(Range.Bound.exclusive(start), Range.Bound.exclusive(end)));
    }

    public QueryPredicateBuilder<T> whereBetween(String property, Date start, Date end) {
        return between(true, property, Range.of(Range.Bound.exclusive(start), Range.Bound.exclusive(end)));
    }

    public QueryPredicateBuilder<T> whereNotBetween(String property, Range<? extends Comparable<?>> range) {
        return this.predicate(true, new NotBetweenSpecification<T>(property, range));
    }

    public QueryPredicateBuilder<T> whereIn(String property, Object... values) {
        return in(true, property, values);
    }

    public QueryPredicateBuilder<T> whereNotIn(String property, Object... values) {
        return notIn(true, property, values);
    }

    public QueryPredicateBuilder<T> whereLike(String property, String... patterns) {
        return this.predicate(true, new LikeSpecification<T>(property, patterns));
    }

    public QueryPredicateBuilder<T> whereNotLike(String property, String... patterns) {
        return this.predicate(true, new NotLikeSpecification<T>(property, patterns));
    }

    public QueryPredicateBuilder<T> whereNull(String property) {
        return eq(property, (Object) null);
    }

    public QueryPredicateBuilder<T> whereNotNull(String property) {
        return ne(property, (Object) null);
    }

    public void whereDate() {

    }

    public void whereMonth() {

    }

    public void whereDay() {

    }

    public void whereYear() {

    }

    public void whereTime() {

    }

    public void whereColumn() {

    }

    public void whereExists() {

    }

    public void orderBy() {

    }

    public void orderByDesc() {

    }

    public void limit() {

    }

    public void whereJsonContains() {

    }

    public void whereJsonLength() {

    }

    public void latest() {

    }

    public void first() {

    }

    public void inRandomOrder() {

    }

    public void reorder() {

    }

    public void groupBy() {

    }

    public void having() {

    }


    public QueryPredicateBuilder<T> eq(String property, Object... values) {
        return eq(true, property, values);
    }

    public QueryPredicateBuilder<T> eq(boolean condition, String property, Object... values) {
        return this.predicate(condition, new EqualSpecification<T>(property, values));
    }

    public QueryPredicateBuilder<T> ne(String property, Object... values) {
        return ne(true, property, values);
    }

    public QueryPredicateBuilder<T> ne(boolean condition, String property, Object... values) {
        return this.predicate(condition, new NotEqualSpecification<T>(property, values));
    }

    public QueryPredicateBuilder<T> gt(String property, Comparable<?> compare) {
        return gt(true, property, compare);
    }

    public QueryPredicateBuilder<T> gt(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new GtSpecification<T>(property, compare));
    }

    public QueryPredicateBuilder<T> ge(String property, Comparable<?> compare) {
        return ge(true, property, compare);
    }

    public QueryPredicateBuilder<T> ge(boolean condition, String property, Comparable<? extends Object> compare) {
        return this.predicate(condition, new GeSpecification<T>(property, compare));
    }

    public QueryPredicateBuilder<T> lt(String property, Comparable<?> number) {
        return lt(true, property, number);
    }

    public QueryPredicateBuilder<T> lt(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new LtSpecification<T>(property, compare));
    }

    public QueryPredicateBuilder<T> le(String property, Comparable<?> compare) {
        return le(true, property, compare);
    }

    public QueryPredicateBuilder<T> le(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new LeSpecification<T>(property, compare));
    }

    public QueryPredicateBuilder<T> between(String property, Range<? extends Comparable<?>> range) {
        return between(true, property, range);
    }

    public QueryPredicateBuilder<T> between(boolean condition, String property, Range<? extends Comparable<?>> range) {
        return this.predicate(condition, new BetweenSpecification<T>(property, range));
    }

    public QueryPredicateBuilder<T> like(String property, String... patterns) {
        return like(true, property, patterns);
    }

    public QueryPredicateBuilder<T> like(boolean condition, String property, String... patterns) {
        return this.predicate(condition, new LikeSpecification<T>(property, patterns));
    }

    public QueryPredicateBuilder<T> notLike(String property, String... patterns) {
        return notLike(true, property, patterns);
    }

    public QueryPredicateBuilder<T> notLike(boolean condition, String property, String... patterns) {
        return this.predicate(condition, new NotLikeSpecification<T>(property, patterns));
    }

    public QueryPredicateBuilder<T> in(String property, Object... values) {
        return this.in(true, property, values);
    }

    public QueryPredicateBuilder<T> in(boolean condition, String property, Object... values) {
        return this.predicate(condition, new InSpecification<T>(property, values));
    }

    public QueryPredicateBuilder<T> notIn(String property, Object... values) {
        return this.notIn(true, property, values);
    }

    public QueryPredicateBuilder<T> notIn(boolean condition, String property, Object... values) {
        return this.predicate(condition, new NotInSpecification<T>(property, values));
    }

    public QueryPredicateBuilder<T> predicate(Specification specification) {
        return predicate(true, specification);
    }

    public QueryPredicateBuilder<T> predicate(boolean condition, Specification specification) {
        if (condition) {
            this.specifications.add(specification);
        }
        return this;
    }

    public List<Specification<T>> getSpecifications() {
        return specifications;
    }

    public Specification<T> build() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate[] predicates = new Predicate[specifications.size()];
            for (int i = 0; i < specifications.size(); i++) {
                predicates[i] = specifications.get(i).toPredicate(root, query, cb);
            }
            if (Objects.equals(predicates.length, 0)) {
                return null;
            }
            return BooleanOperator.OR.equals(operator) ? cb.or(predicates) : cb.and(predicates);
        };
    }

}
