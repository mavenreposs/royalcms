package io.github.mavenreposs.royalcms.component.support;

import java.util.HashMap;
import java.util.Map;

public class DriverManager implements DriverInterface {

    private final Map<String, Object> driverMap = new HashMap<>();

    public DriverManager() {
    }

    public void registerDefaultDriver(String storageClassName) {
        driverMap.put("default", storageClassName);
    }

    public void registerDriver(String alias, String storageClassName) {
        driverMap.put(alias, storageClassName);
    }

    public void removeDriver(String alias) {
        driverMap.remove(alias);
    }

    public Object getDriver(String alias) {
        return driverMap.get(alias);
    }

    public Object getDefaultDriver() {
        return driverMap.get("default");
    }

    public Object driver() {
        return getDefaultDriver();
    }

    public Object driver(String alias) {
        return getDriver(alias);
    }

    public boolean hasDriver(String alias) {
        return driverMap.containsKey(alias);
    }

}
