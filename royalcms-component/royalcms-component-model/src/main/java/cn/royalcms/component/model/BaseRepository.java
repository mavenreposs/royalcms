package cn.royalcms.component.model;

import cn.royalcms.component.model.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * 基类的数据访问接口(继承了CrudRepository,PagingAndSortingRepository,
 * JpaSpecificationExecutor的特性)
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepositoryImplementation<T, ID> {


    String domainClassName();

    Optional<T> find(ID id);

    /**
     * 获取列表中的IDs数组
     *
     * @param rows      列表对象
     * @param filedName 指定int字段
     * @return int数组
     */
    List<Integer> getIds(List<T> rows, String filedName);

    /**
     * 获取列表中的IDs数组
     *
     * @param query     查询对象
     * @param filedName 指定int字段
     * @return int数组
     */
    List<Integer> getIdsWithFindAll(BaseQuery query, String filedName);

    /**
     * 使用QBL进行查询列表
     *
     * @param query BaseQuery
     */
    List<T> findAll(BaseQuery query);

    /**
     * 封装分页查询
     *
     * @param query BaseQuery
     * @param pageable Pageable
     */
    Page<T> findAll(BaseQuery query, Pageable pageable);

    /**
     * 封装排序查询
     *
     * @param query BaseQuery
     * @param sort Sort
     */
    List<T> findAll(BaseQuery query, Sort sort);

    /**
     * 使用QBL定位记录
     */
    Optional<T> findOne(BaseQuery query);

    /**
     * 从记录中获取单个值。该方法将直接返回该字段的值
     */
    <U> Optional<U> value(BaseQuery query, Function<? super T, ? extends U> mapper);

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     */
    <U> List<U> pluck(BaseQuery query, Function<? super T, ? extends U> mapper);

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     */
    <K, V> Map<K, V> pluck(BaseQuery query, Function<? super T, ? extends V> value, Function<? super T, ? extends K> key);

    /**
     * 查询统计条数
     * @param query BaseQuery
     * @return long
     */
    long count(BaseQuery query);

    /**
     * 计算字段值之和
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y sum(BaseQuery query, String fieldName);

    /**
     * 计算字段值之和
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y sum(BaseQuery query, String fieldName, Class<Y> type);

    /**
     * 计算字段值最小值
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y min(BaseQuery query, String fieldName);

    /**
     * 计算字段值最小值
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y min(BaseQuery query, String fieldName, Class<Y> type);

    /**
     * 计算字段值最大值
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y max(BaseQuery query, String fieldName);

    /**
     * 计算字段值最大值
     * @param query BaseQuery
     * @return long
     */
    <Y extends Number> Y max(BaseQuery query, String fieldName, Class<Y> type);

    /**
     * 计算字段值平均值
     * @param query BaseQuery
     * @return long
     */
    Double avg(BaseQuery query, String fieldName);

    /**
     * 更新方法
     *
     * @param t
     * @param updateFileds
     * @param where
     * @return int
     */
    int update(T t, BaseQuery where, String... updateFileds);

    /**
     * 根据唯一主键更新方法
     *
     * @param t
     * @param id
     * @param updateFileds
     * @return int
     */
    int updateById(T t, ID id, String... updateFileds);

    /**
     * 根据查询条件批量删除相关数据
     * @param query 查询条件
     * @return 更新结果
     */
    int delete(BaseQuery query);

    /**
     * 根据唯一主键删除相关数据
     * @param ids 主键ID
     * @return 更新结果
     */
    int deleteByIds(ID... ids);


}
