package cn.royalcms.facades.collection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RC_Collection {
    private RC_Collection() {
    }

    /**
     * 从记录中获取单个值。该方法将直接返回该字段的值
     *
     * @param result Optional
     * @param mapper Function
     * @param <T> Optional
     * @param <U> Function
     * @return Optional
     */
    public static  <T, U> Optional<U> value(Optional<T> result, Function<? super T, ? extends U> mapper) {
        return result.map(mapper);
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     *
     * @param list List
     * @param mapper Function
     * @param <T> List
     * @param <U> Function
     * @return List
     */
    public static  <T, U> List<U> pluck(List<T> list, Function<? super T, ? extends U> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     *
     * @param list List
     * @param value Function
     * @param key Function
     * @param <T> List
     * @param <K> Function
     * @param <V> Function
     * @return Map
     */
    public static  <T, K, V> Map<K, V> pluck(List<T> list, Function<? super T, ? extends V> value, Function<? super T, ? extends K> key) {
        Map<K, V> map = new LinkedHashMap<>();
        list.forEach(row -> {
            map.put(key.apply(row), value.apply(row));
        });
        return map;
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     *
     * @param list List
     * @param key Function
     * @param <T> List
     * @param <K> Function
     * @return Map
     */
    public static  <T, K> Map<K, T> pluckMap(List<T> list, Function<? super T, ? extends K> key) {
        Map<K, T> map = new LinkedHashMap<>();
        list.forEach(row -> {
            map.put(key.apply(row), row);
        });
        return map;
    }

    /**
     * 如果你想获取单列数据的集合，则可以使用 pluck 方法
     *
     * @param list List
     * @param key Function
     * @param values Function
     * @param <T> List
     * @param <K> Function
     * @param <V> Function
     * @return Map
     */
    @SafeVarargs
    public static  <T, K, V> Map<K, List<V>> pluckMap(List<T> list, Function<? super T, ? extends K> key, Function<? super T, ? extends V>... values) {
        Map<K, List<V>> map = new LinkedHashMap<>();
        list.forEach(row -> {
            List<V> newValus = Arrays.stream(values).map(value -> value.apply(row)).collect(Collectors.toList());
            map.put(key.apply(row), newValus);
        });
        return map;
    }

}
