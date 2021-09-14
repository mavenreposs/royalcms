package cn.royalcms.component.model.impl;

import cn.royalcms.facades.log.RC_Log;
import cn.royalcms.facades.string.RC_String;
import cn.royalcms.component.model.BaseRepository;
import cn.royalcms.component.model.query.BaseQuery;
import cn.royalcms.component.model.query.BaseQueryPredicateBuilder;
import cn.royalcms.component.model.query.QueryToSpecification;
import io.github.mavenreposs.illuminate4j.support.Callable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    private final Class<T> domainClass;
    private final EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
    }

    @Override
    public String domainClassName() {
        return domainClass.getName();
    }

    public Optional<T> find(ID id) {
        return findById(id);
    }

    /**
     * 自定义查询条件转换实现
     * <p>
     * (non-Javadoc)
     */
    private Specification<T> getConditonByQuery(BaseQuery query) {
        return new QueryToSpecification(query);
    }

    /**
     * 获取列表中的IDs数组
     *
     * @param rows      列表对象
     * @param filedName 指定int字段
     * @return int数组
     */
    public List<Integer> getIds(List<T> rows, String filedName) {
        return rows.stream().map(row -> {
            try {
                return (Integer) Callable.get(row, filedName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }).collect(Collectors.toList());
    }

    /**
     * 获取列表中的IDs数组
     *
     * @param query     查询对象
     * @param filedName 指定int字段
     * @return int数组
     */
    public List<Integer> getIdsWithFindAll(BaseQuery query, String filedName) {
        List<T> rows = findAll(query);
        return getIds(rows, filedName);
    }

    /**
     * 封装自定义组合查询列表方法
     * <p>
     * (non-Javadoc)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(BaseQuery query) {
        if (query.getSort() != null) {
            return findAll(getConditonByQuery(query), query.getSort());
        } else if (query.getPage() != null) {
            return (List<T>) findAll(getConditonByQuery(query), query.getPage());
        } else {
            return (List<T>) findAll(getConditonByQuery(query));
        }
    }

    /**
     * 自定义组合查询分页方法
     *
     * @param query BaseQuery
     * @param pageable Pageable
     * @return Page
     */
    @Override
    public Page<T> findAll(BaseQuery query, Pageable pageable) {
        return findAll(getConditonByQuery(query), pageable);
    }

    /**
     * 查询条件
     * @param query BaseQuery
     * @return Optional
     */
    @Override
    public Optional<T> findOne(BaseQuery query) {
        return findOne(getConditonByQuery(query));
    }

    /**
     * 从记录中获取单个值。该方法将直接返回该字段的值
     * @param query BaseQuery
     * @param mapper Function
     * @param <U> Optional
     * @return Optional
     */
    public <U> Optional<U> value(BaseQuery query, Function<? super T, ? extends U> mapper) {
        Optional<T> result = findOne(getConditonByQuery(query));
        return result.map(mapper);
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     * @param query BaseQuery
     * @param mapper Function
     * @param <U> List
     * @return List
     */
    public <U> List<U> pluck(BaseQuery query, Function<? super T, ? extends U> mapper) {
        List<T> result = findAll(getConditonByQuery(query));
        return result.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     * @param query BaseQuery
     * @param value Function
     * @param key Function
     * @param <K> Function
     * @param <V> Function
     * @return Map
     */
    public <K, V> Map<K, V> pluck(BaseQuery query, Function<? super T, ? extends V> value, Function<? super T, ? extends K> key) {
        List<T> result = findAll(getConditonByQuery(query));
        Map<K, V> map = new LinkedHashMap<>();
        result.forEach(row -> {
            map.put(key.apply(row), value.apply(row));
        });
        return map;
    }

    /**
     * 查询统计条数
     * @param query BaseQuery
     * @return long
     */
    public long count(BaseQuery query) {
        return count(getConditonByQuery(query));
    }

    private <Y> Class<Y> getClassTypeByFieldName(String fieldName) {
        Class<Y> clazz;

        try {
            String methodName = "get" + RC_String.ucfirst(fieldName);
            Method method = domainClass.getMethod(methodName);
            clazz = (Class<Y>) method.getReturnType();
        } catch (NoSuchMethodException e) {
            clazz = (Class<Y>) Integer.class;
        }
        return clazz;
    }

    /**
     * 计算字段值之和
     * @param query BaseQuery
     * @param fieldName String
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y sum(BaseQuery query, String fieldName) {
        return sum(query, fieldName, getClassTypeByFieldName(fieldName));
    }

    /**
     * 计算字段值之和
     * @param query BaseQuery
     * @param fieldName String
     * @param type Class
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y sum(BaseQuery query, String fieldName, Class<Y> type) {
        return executeAggregateQuery(getSumQuery(getConditonByQuery(query), getDomainClass(), fieldName, type));
    }

    /**
     * 计算字段值最小值
     * @param query BaseQuery
     * @param fieldName String
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y min(BaseQuery query, String fieldName) {
        return min(query, fieldName, getClassTypeByFieldName(fieldName));
    }

    /**
     * 计算字段值最小值
     * @param query BaseQuery
     * @param fieldName String
     * @param type Class
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y min(BaseQuery query, String fieldName, Class<Y> type) {
        return executeAggregateQuery(getMinQuery(getConditonByQuery(query), getDomainClass(), fieldName, type));
    }

    /**
     * 计算字段值最大值
     * @param query BaseQuery
     * @param fieldName String
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y max(BaseQuery query, String fieldName) {
        return max(query, fieldName, getClassTypeByFieldName(fieldName));
    }

    /**
     * 计算字段值最大值
     * @param query BaseQuery
     * @param fieldName String
     * @param type Class
     * @param <Y> Number
     * @return long
     */
    public <Y extends Number> Y max(BaseQuery query, String fieldName, Class<Y> type) {
        return executeAggregateQuery(getMaxQuery(getConditonByQuery(query), getDomainClass(), fieldName, type));
    }

    /**
     * 计算字段值平均值
     * @param query BaseQuery
     * @param fieldName String
     * @return long
     */
    public Double avg(BaseQuery query, String fieldName) {
        return executeAggregateQuery(getAvgQuery(getConditonByQuery(query), getDomainClass(), fieldName));
    }


    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Override
    protected CrudMethodMetadata getRepositoryMethodMetadata() {
        return super.getRepositoryMethodMetadata();
    }

    @Override
    protected Class<T> getDomainClass() {
        return super.getDomainClass();
    }

    public T findOne(ID id) {
        return super.getOne(id);
    }

    @Override
    protected QueryHints getQueryHints() {
        return super.getQueryHints();
    }

    @Override
    public T getOne(ID id) {
        return super.getOne(id);
    }

    @Override
    public boolean existsById(ID id) {
        return super.existsById(id);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return super.findAllById(ids);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return super.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return super.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return super.findAll(spec, sort);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return super.findOne(example);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return super.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return super.exists(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return super.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return super.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return super.findAll(example, pageable);
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public long count(Specification<T> spec) {
        return super.count(spec);
    }

    @Override
    protected <S extends T> Page<S> readPage(TypedQuery<S> query, Class<S> domainClass, Pageable pageable,
                                             Specification<S> spec) {
        return super.readPage(query, domainClass, pageable, spec);
    }

    @Override
    protected TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable) {
        return super.getQuery(spec, pageable);
    }

    @Override
    protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Pageable pageable) {
        return super.getQuery(spec, domainClass, pageable);
    }

    @Override
    protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
        return super.getQuery(spec, sort);
    }

    @Override
    protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Sort sort) {
        return super.getQuery(spec, domainClass, sort);
    }

    @Override
    protected <S extends T> TypedQuery<Long> getCountQuery(Specification<S> spec, Class<S> domainClass) {
        return super.getCountQuery(spec, domainClass);
    }

    /**
     * 封装自定义组合查询排序列表方法
     * @param query BaseQuery
     * @param sort Sort
     * @return List
     */
    @Override
    public List<T> findAll(BaseQuery query, Sort sort) {
        return findAll(getConditonByQuery(query), sort);
    }

    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return super.saveAll(entities);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }

    @Modifying(clearAutomatically=true)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return super.saveAndFlush(entity);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
        super.setRepositoryMethodMetadata(crudMethodMetadata);
    }

    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public void deleteById(ID id) {
        super.deleteById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void delete(T entity) {
        super.delete(entity);
    }

    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        super.deleteAll(entities);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteInBatch(Iterable<T> entities) {
        super.deleteInBatch(entities);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteAll() {
        super.deleteAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteAllInBatch() {
        super.deleteAllInBatch();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void flush() {
        super.flush();
    }

    /**
     * 自定义更新update方法
     * @param t 实体类
     * @param updateFileds 指定更新的字段
     * @return 更新结果
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int update(T t, BaseQuery where, String... updateFileds) {
        CriteriaBuilder cb = entityManager.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaUpdate<T> update = (CriteriaUpdate<T>) cb.createCriteriaUpdate(t.getClass());
        Root<T> root = update.from((Class<T>) t.getClass());

        for (String fieldName : updateFileds) {
            try {
                Property property = new PropertyUtils().getProperty(t.getClass(), fieldName);
                Object o = property.get(t);
                update.set(fieldName, o);
            } catch (Exception e) {
                RC_Log.error("update error:" + e);
            }
        }
        update.where(BaseQueryPredicateBuilder.getPredicate2(root, cb, where));
        return entityManager.createQuery(update).executeUpdate();
    }

    /**
     * 根据唯一主键更新相关数据
     * @param t 实体类
     * @param id 主键ID
     * @param updateFileds 指定更新的字段
     * @return 更新结果
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateById(T t, ID id, String... updateFileds) {
        CriteriaBuilder cb = entityManager.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaUpdate<T> update = (CriteriaUpdate<T>) cb.createCriteriaUpdate(t.getClass());
        Root<T> root = update.from((Class<T>) t.getClass());
        for (String fieldName : updateFileds) {
            try {
                Property property = new PropertyUtils().getProperty(t.getClass(), fieldName);
                Object o = property.get(t);
                update.set(fieldName, o);
            } catch (Exception e) {
                RC_Log.error("update error:" + e);
            }
        }
        //定位主键信息
        Iterable<String> idAttributeNames = entityInformation.getIdAttributeNames();

        for (String key : idAttributeNames) {
            if (key != null && !key.equals("")) {
                update.where(cb.equal(root.get(key), id));
                break;
            }
        }
        return entityManager.createQuery(update).executeUpdate();
    }

    /**
     * 根据查询条件批量删除相关数据
     * @param query 查询条件
     * @return 更新结果
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int delete(BaseQuery query) {
        CriteriaBuilder cb = entityManager.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaDelete<T> delete = (CriteriaDelete<T>) cb.createCriteriaDelete(domainClass);
        Root<T> root = delete.from((Class<T>) domainClass);
        delete.where(BaseQueryPredicateBuilder.getPredicate2(root, cb, query));
        return entityManager.createQuery(delete).executeUpdate();
    }

    /**
     * 根据唯一主键删除相关数据
     * @param ids 主键ID
     * @return 更新结果
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByIds(ID... ids) {
        CriteriaBuilder cb = entityManager.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaDelete<T> delete = (CriteriaDelete<T>) cb.createCriteriaDelete(domainClass);
        Root<T> root = delete.from((Class<T>) domainClass);

        //定位主键信息
        Iterable<String> idAttributeNames = entityInformation.getIdAttributeNames();

        for (String key : idAttributeNames) {
            if (key != null && !key.equals("")) {
                delete.where(root.get(key).in(ids));
                break;
            }
        }
        return entityManager.createQuery(delete).executeUpdate();
    }

    /**
     * Executes a count query and transparently sums up all values returned.
     *
     * @param query must not be {@literal null}.
     * @return Integer
     */
    private static <Y extends Number> Y executeAggregateQuery(TypedQuery<Y> query) {

        Assert.notNull(query, "TypedQuery must not be null!");

        List<Y> totals = query.getResultList();

        return totals.get(0);
    }

    /**
     * Creates a new count query for the given {@link Specification}.
     * @param spec Specification
     * @param domainClass Class
     * @param fieldName String
     * @param resultClass Class
     * @param <S> Specification
     * @param <Y> Number
     * @return TypedQuery
     */
    protected <S extends T, Y extends Number> TypedQuery<Y> getSumQuery(@Nullable Specification<S> spec, Class<S> domainClass, String fieldName, Class<Y> resultClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Y> query = builder.createQuery(resultClass);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
        Expression<Y> path = root.get(fieldName);

        query.select(builder.sum(path));

        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.<Order> emptyList());

        return entityManager.createQuery(query);
    }

    /**
     * Creates a new count query for the given {@link Specification}.
     *
     * @param spec can be {@literal null}.
     * @param domainClass must not be {@literal null}.
     * @param fieldName String
     * @param resultClass Class
     * @param <Y> TypedQuery
     * @param <S> Specification
     *
     * @return TypedQuery
     */
    protected <S extends T, Y extends Number> TypedQuery<Y> getMinQuery(@Nullable Specification<S> spec, Class<S> domainClass, String fieldName, Class<Y> resultClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Y> query = builder.createQuery(resultClass);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
        Expression<Y> path = root.get(fieldName);

        query.select(builder.min(path));

        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.<Order> emptyList());

        return entityManager.createQuery(query);
    }

    /**
     * Creates a new count query for the given {@link Specification}.
     *
     * @param spec can be {@literal null}.
     * @param domainClass must not be {@literal null}.
     * @param fieldName String
     * @param resultClass Class
     * @param <S> Specification
     * @param <Y> TypedQuery
     *
     * @return TypedQuery
     */
    protected <S extends T, Y extends Number> TypedQuery<Y> getMaxQuery(@Nullable Specification<S> spec, Class<S> domainClass, String fieldName, Class<Y> resultClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Y> query = builder.createQuery(resultClass);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
        Expression<Y> path = root.get(fieldName);

        query.select(builder.max(path));

        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.<Order> emptyList());

        return entityManager.createQuery(query);
    }

    /**
     * Creates a new count query for the given {@link Specification}.
     *
     * @param spec can be {@literal null}.
     * @param domainClass must not be {@literal null}.
     * @param fieldName String
     * @param <S> Specification
     *
     * @return TypedQuery
     */
    protected <S extends T> TypedQuery<Double> getAvgQuery(@Nullable Specification<S> spec, Class<S> domainClass, String fieldName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = builder.createQuery(Double.class);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);

        query.select(builder.avg(root.get(fieldName)));

        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.<Order> emptyList());

        return entityManager.createQuery(query);
    }

    /**
     * Applies the given {@link Specification} to the given {@link CriteriaQuery}.
     *
     * @param spec can be {@literal null}.
     * @param domainClass must not be {@literal null}.
     * @param query must not be {@literal null}.
     * @param <S> CriteriaQuery
     * @param <U> Specification
     * @return Root
     */
    private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,
                                                                  CriteriaQuery<S> query) {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(query, "CriteriaQuery must not be null!");

        Root<U> root = query.from(domainClass);

        if (spec == null) {
            return root;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Predicate predicate = spec.toPredicate(root, query, builder);

        if (predicate != null) {
            query.where(predicate);
        }

        return root;
    }

}
