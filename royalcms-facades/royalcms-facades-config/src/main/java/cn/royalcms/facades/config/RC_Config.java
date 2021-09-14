package cn.royalcms.facades.config;

import cn.royalcms.component.config.contracts.ConfigItemRepositoryInterface;
import cn.royalcms.component.config.proxy.ConfigInvocationHandler;
import cn.royalcms.facades.serializer.RC_Serializer;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class RC_Config {

    private ConfigItemRepositoryInterface repository;

    private volatile static RC_Config singleton;

    private RC_Config() {}

    public static RC_Config getSingleton() {
        synchronized (RC_Config.class) {
            if (singleton == null) {
                singleton = new RC_Config();
                singleton.repository = (ConfigItemRepositoryInterface) singleton.proxy(ConfigItemRepositoryInterface.class);
            }
        }
        return singleton;
    }

    public final Object proxy(Class<?> type) {
        ConfigInvocationHandler handler = new ConfigInvocationHandler();
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
    }

    /**
     * Get the all codes.
     *
     * @return array
     */
    public static ArrayList<String> allKeys()
    {
        return getSingleton().repository.allKeys();
    }

    /**
     * Get the all Items.
     *
     * @return array
     */
    public static HashMap<String, Object> all()
    {
        return getSingleton().repository.all();
    }

    /**
     * Clean the caches.
     *
     * @return Boolean
     */
    public static Boolean clearCache() {
        return getSingleton().repository.clearCache();
    }

    /**
     * Determine if the given configuration value exists.
     *
     * @param  key Key
     * @return bool
     */
    public static Boolean has(String key)
    {
        return getSingleton().repository.has(key);
    }

    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @return String
     */
    public static Object get(String key)
    {
        return getSingleton().repository.get(key);
    }

    public static Integer getInteger(String key) {
        Object v = get(key);
        return Integer.valueOf(v.toString());
    }

    public static Integer getInteger(String key, Integer defaultValue) {
        Object v = get(key, defaultValue);
        return Integer.valueOf(v.toString());
    }

    public static Boolean getBoolean(String key) {
        String v = (String) get(key);
        int vInt = Integer.parseInt(v);
        return vInt == 1;
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        String v = (String) get(key, defaultValue);
        int vInt = Integer.parseInt(v);
        return vInt == 1;
    }

    public static String getString(String key) {
        Object v = get(key);
        return v.toString();
    }

    public static String getString(String key, String defaultValue) {
        Object v = get(key, defaultValue);
        return v.toString();
    }

    public static Map<String, Object> getSerialize(String key) {
        Object v = get(key);
        return RC_Serializer.unserialize(v.toString());
    }

    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @param  defaultValue Value
     * @return mixed
     */
    public static Object get(String key, Object defaultValue)
    {
        return getSingleton().repository.get(key, defaultValue);
    }

    /**
     * Set a given configuration value.
     *
     * @param  key Key
     * @param   value Value
     */
    public static void set(String key, Object value)
    {
        getSingleton().repository.set(key, value);
    }

    /**
     * Write a given configuration value.
     *
     * @param  key Key
     * @param   value Value
     */
    public static void write(String key, Object value)
    {
        getSingleton().repository.write(key, value);
    }

    /**
     * Delete the specified configuration value.
     *
     * @param key Key
     * @return bool
     */
    public static Boolean delete(String key)
    {
        return getSingleton().repository.delete(key);
    }

    /**
     * Add a given configuration value.
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @return bool
     */
    public static Boolean add(String group, String key, Object value)
    {
        return getSingleton().repository.add(group, key, value);
    }

    /**
     * Add a given configuration value.
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @param options Options
     * @return bool
     */
    public static Boolean add(String group, String key, Object value, HashMap<String, String> options)
    {
        return getSingleton().repository.add(group, key, value, options);
    }

    /**
     * Change a given configuration value.
     *
     * @param group String
     * @param key String
     * @param value String
     * @return bool
     */
    public static Boolean change(String group, String key, Object value)
    {
        return getSingleton().repository.change(group, key, value);
    }

    /**
     * Change a given configuration value.
     *
     * @param group String
     * @param key String
     * @param value String
     * @param options HashMap
     * @return bool
     */
    public static Boolean change(String group, String key, Object value, HashMap<String, String>options)
    {
        return getSingleton().repository.change(group, key, value, options);
    }

}
