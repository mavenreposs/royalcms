package cn.royalcms.component.config.contracts;

import java.util.ArrayList;
import java.util.HashMap;

public interface ConfigItemRepositoryInterface {

    /**
     * 载入数据
     */
    void load();

    /**
     * Get the all codes.
     *
     * @return array
     */
    ArrayList<String> allKeys();

    /**
     * Clean the caches.
     *
     * @return Boolean
     */
    Boolean clearCache();

    /**
     * Get the all Items.
     *
     * @return HashMap
     */
    HashMap<String, Object> all();

    /**
     * Determine if the given configuration value exists.
     *
     * @param  key Key
     * @return bool
     */
    Boolean has(String key);

    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @return String
     */
    Object get(String key);

    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @param  defaultValue Value
     * @return mixed
     */
    Object get(String key, Object defaultValue);

    /**
     * Set a given configuration value.
     *
     * @param  key Key
     * @param   value Value
     */
    void set(String key, Object value);

    /**
     * Write a given configuration value.
     *
     * @param  key Key
     * @param   value Value
     */
    void write(String key, Object value);

    /**
     * Delete the specified configuration value.
     *
     * @param key Key
     * @return bool
     */
    Boolean delete(String key);

    /**
     * Add a given configuration value.
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @return bool
     */
    Boolean add(String group, String key, Object value);

    /**
     * Add a given configuration value.
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @param options Options
     * @return bool
     */
    Boolean add(String group, String key, Object value, HashMap<String, String> options);

    /**
     * Change a given configuration value.
     *
     * @param group String
     * @param key String
     * @param value String
     * @return bool
     */
    Boolean change(String group, String key, Object value);

    /**
     * Change a given configuration value.
     *
     * @param group String
     * @param key String
     * @param value String
     * @param options HashMap
     * @return bool
     */
    Boolean change(String group, String key, Object value, HashMap<String, String>options);

}
