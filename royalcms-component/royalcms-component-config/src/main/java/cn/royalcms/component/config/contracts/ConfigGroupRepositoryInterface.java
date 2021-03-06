package cn.royalcms.component.config.contracts;

import java.util.HashMap;

public interface ConfigGroupRepositoryInterface {

    /**
     * 载入数据
     */
    void load();

    /**
     * 获取所有的组
     *
     * @return HashMap
     */
    HashMap<String, Integer> allGroups();

    Integer addGroup(String code);

    /**
     * 根据code删除一个组
     *
     * @param code String
     * @return Boolean
     */
    Boolean deleteGroup(String code);

    /**
     * 根据code判断是否有指定的组
     *
     * @param code String
     * @return Boolean
     */
    Boolean hasGroup(String code);

}
