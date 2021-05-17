package io.github.mavenreposs.royalcms.component.config.manager;

import io.github.mavenreposs.royalcms.component.config.contracts.ConfigItemRepositoryInterface;

import java.util.HashMap;

public class ItemManager extends AbstractManager {

    /**
     * Create a new config instance.
     *
     * @param repository ConfigItemRepositoryInterface
     */
    public ItemManager(ConfigItemRepositoryInterface repository) {
        super(repository);
    }

    @Override
    public void load() {

    }

    /**
     * Get the all Items.
     * @return HashMap<String, String>
     */
    public HashMap<String, Object> all()
    {
        return this.getRepository().all();
    }


    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @return <T>
     */
    public Object get(String key)
    {
        return this.getRepository().get(key);
    }

    /**
     * Get the specified configuration value.
     *
     * @param  key Key
     * @param  defaultValue Value
     * @return <T>
     */
    public Object get(String key, Object defaultValue)
    {
        return this.getRepository().get(key, defaultValue);
    }


    /**
     * Set a given configuration value.
     *
     * @param  key Key
     * @param  value Value
     */
    public void set(String key, Object value)
    {
        this.getRepository().set(key, value);
    }


    /**
     * Write a given configuration value.
     *
     * @param key Key
     * @param value Value
     */
    public void write(String key, Object value)
    {
        this.getRepository().write(key, value);
    }

    /**
     * Determine if the given configuration value exists.
     *
     * @param  key Key
     * @return bool
     */
    public Boolean has(String key)
    {
        return this.getRepository().has(key);
    }


    /**
     * 添加配置项
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @return bool
     */
    public Boolean add(String group, String key, Object value)
    {
        return this.getRepository().add(group, key, value);
    }

    /**
     * 添加配置项
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @param options HashMap
     * @return bool
     */
    public Boolean add(String group, String key, Object value, HashMap<String, String> options)
    {
        return this.getRepository().add(group, key, value, options);
    }

    /**
     * 修改配置项
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @return bool
     */
    public Boolean change(String group, String key, Object value)
    {
        return this.getRepository().change(group, key, value);
    }

    /**
     * 修改配置项
     *
     * @param group Group
     * @param key Key
     * @param value Value
     * @param options HashMap
     * @return bool
     */
    public Boolean change(String group, String key, Object value, HashMap<String, String> options)
    {
        return this.getRepository().change(group, key, value, options);
    }

    /**
     * 删除某个配置项
     * @param key Key
     * @return bool
     */
    public Boolean delete(String key)
    {
        return this.getRepository().delete(key);
    }


}
