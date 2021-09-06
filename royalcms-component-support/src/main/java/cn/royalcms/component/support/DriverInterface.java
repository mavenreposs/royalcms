package cn.royalcms.component.support;

public interface DriverInterface {

    void registerDefaultDriver(String storageClassName);

    void registerDriver(String alias, String storageClassName);

    void removeDriver(String alias);

    Object getDriver(String alias);

    Object getDefaultDriver();

    Object driver();

    Object driver(String alias);

    boolean hasDriver(String alias);

}
