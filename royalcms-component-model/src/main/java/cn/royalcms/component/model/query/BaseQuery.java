package cn.royalcms.component.model.query;

import cn.dscmall.component.bean.SpringBeanFactory;
import cn.royalcms.component.model.BaseRepository;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


public class BaseQuery {

    private Pageable page;

    private Sort sort;

    private WhereClauseInterface clause;

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public WhereClauseInterface getClause() {
        return clause;
    }

    public void setClause(WhereClauseInterface clause) {
        this.clause = clause;
    }

    /**
     * 分页设置
     * @param start 起始页码
     * @param size 每页大小
     * @return BaseQuery
     */
    public BaseQuery pageOf(int start, int size) {
        this.setPage(PageRequest.of(start, size, this.getSort()));
        return this;
    }

    /**
     * 倒序排序设置
     * @param properties 实体类字段属性
     * @return BaseQuery
     */
    public BaseQuery descSortBy(String... properties) {
        this.setSort(Sort.by(Sort.Direction.DESC, properties));
        return this;
    }

    /**
     * 正序排序设置
     * @param properties 实体类字段属性
     * @return BaseQuery
     */
    public BaseQuery ascSortBy(String... properties) {
        this.setSort(Sort.by(Sort.Direction.ASC, properties));
        return this;
    }

    /**
     * 清除Page设置
     * @return BaseQuery
     */
    public BaseQuery clearPage() {
        this.setPage(null);
        return this;
    }

    /**
     * 清除Sort设置
     * @return BaseQuery
     */
    public BaseQuery clearSort() {
        this.setSort(null);
        return this;
    }

    private static Class<?> queryRepository(BaseQuery query) {
        Annotation anno = query.getClass().getAnnotation(QBindRepository.class);
        if (anno != null) {
            return ((QBindRepository) anno).value();
        } else {
            throw new AnnotationConfigurationException("@QBindRepository annotation not used.");
        }
    }

    private static <T, ID extends Serializable, B extends BaseRepository> B getRepository(Class<?> queryRepository) {
        return (B) SpringBeanFactory.getBean(queryRepository);
    }

    /**
     * 查询统计条数
     * @return long
     */
    public long count() {
        return getRepository(queryRepository(this)).count(this);
    }

    /**
     * 计算字段值之和
     * @return long
     */
    public <Y extends Number> Y sum(String fieldName) {
        return (Y) getRepository(queryRepository(this)).sum(this, fieldName);
    }

    /**
     * 计算字段值之和
     * @return long
     */
    public <Y extends Number> Y sum(String fieldName, Class<Y> type) {
        return (Y) getRepository(queryRepository(this)).sum(this, fieldName, type);
    }

    /**
     * 计算字段值最小值
     * @return long
     */
    public <Y extends Number> Y min(String fieldName) {
        return (Y) getRepository(queryRepository(this)).min(this, fieldName);
    }

    /**
     * 计算字段值最小值
     * @return long
     */
    public <Y extends Number> Y min(String fieldName, Class<Y> type) {
        return (Y) getRepository(queryRepository(this)).min(this, fieldName, type);
    }

    /**
     * 计算字段值最大值
     * @return long
     */
    public <Y extends Number> Y max(String fieldName) {
        return (Y) getRepository(queryRepository(this)).max(this, fieldName);
    }

    /**
     * 计算字段值最大值
     * @return long
     */
    public <Y extends Number> Y max(String fieldName, Class<Y> type) {
        return (Y) getRepository(queryRepository(this)).max(this, fieldName, type);
    }

    /**
     * 计算字段值平均值
     * @return long
     */
    public Double avg(String fieldName) {
        return getRepository(queryRepository(this)).avg(this, fieldName);
    }

    /**
     * 查询条件
     */
    public <U, T> Optional<U> value(Function<? super T, ? extends U> mapper) {
        return getRepository(queryRepository(this)).value(this, mapper);
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     */
    public <U, T> List<U> pluck(Function<? super T, ? extends U> mapper) {
        return getRepository(queryRepository(this)).pluck(this, mapper);
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     */
    public <K, V, T> Map<K, V> pluck(Function<? super T, ? extends V> value, Function<? super T, ? extends K> key) {
        return getRepository(queryRepository(this)).pluck(this, value, key);
    }

    /**
     * 使用QBL定位记录
     */
    public <T> Optional<T> findOne() {
        return (Optional<T>) getRepository(queryRepository(this)).findOne(this);
    }

    /**
     * 使用QBL进行查询列表
     */
    public <T> List<T> findAll() {
        return getRepository(queryRepository(this)).findAll(this);
    }

    /**
     * 封装分页查询
     *
     * @param pageable Pageable
     */
    public <T> Page<T> findAll(Pageable pageable) {
        return getRepository(queryRepository(this)).findAll(this, pageable);
    }

    /**
     * 封装排序查询
     *
     * @param sort Sort
     */
    public <T> List<T> findAll(Sort sort) {
        return getRepository(queryRepository(this)).findAll(this, sort);
    }

    /**
     * 获取列表中的IDs数组
     *
     * @param filedName 指定int字段
     * @return int数组
     */
    public List<Integer> getIdsWithFindAll(String filedName) {
        return getRepository(queryRepository(this)).getIdsWithFindAll(this, filedName);
    }

    /**
     * 获取列表中的IDs数组
     *
     * @param rows      列表对象
     * @param filedName 指定int字段
     * @return int数组
     */
    public <T> List<Integer> getIds(List<T> rows, String filedName) {
        return getRepository(queryRepository(this)).getIds(rows, filedName);
    }

    /**
     * 自定义更新update方法
     * @param entity 实体类
     * @param updateFileds 指定更新的字段
     * @return 更新结果
     */
    public <T> int update(T entity, String... updateFileds) {
        return getRepository(queryRepository(this)).update(entity, this, updateFileds);
    }

    /**
     * 根据唯一主键更新相关数据
     * @param entity 实体类
     * @param id 主键ID
     * @param updateFileds 指定更新的字段
     * @return 更新结果
     */
    public <T, ID extends Serializable> int updateById(T entity, ID id, String... updateFileds) {
        return getRepository(queryRepository(this)).updateById(entity, id, updateFileds);
    }

    /**
     * 根据查询条件批量删除相关数据
     * @param query 查询条件
     * @return 更新结果
     */
    public int delete() {
        return getRepository(queryRepository(this)).delete(this);
    }

    /**
     * 根据唯一主键删除相关数据
     * @param ids 主键ID
     * @return 更新结果
     */
    public <ID> int deleteByIds(ID... ids) {
        return getRepository(queryRepository(this)).deleteByIds(ids);
    }

}
