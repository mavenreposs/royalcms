package cn.royalcms.facades.storage;

import cn.royalcms.facades.bean.RC_Bean;
import cn.royalcms.facades.bean.SpringBeanFactory;
import cn.royalcms.contracts.storage.Cloud;
import cn.royalcms.contracts.storage.Storage;
import cn.royalcms.component.support.DriverManager;

import java.util.Optional;

public class RC_Storage {

    private final DriverManager manager = new DriverManager();

    private volatile static RC_Storage singleton;

    public static RC_Storage getSingleton() {
        synchronized (RC_Storage.class) {
            if (singleton == null) {
                singleton = new RC_Storage();
            }
        }
        return singleton;
    }

    private RC_Storage() {}

    public static DriverManager getManager() {
        return getSingleton().manager;
    }

    public static void registerDefaultDriver(String storageClassName) {
        getManager().registerDefaultDriver(storageClassName);
    }

    public static void registerDriver(String alias, String storageClassName) {
        getManager().registerDriver(alias, storageClassName);
    }

    public static void removeDriver(String alias) {
        getManager().removeDriver(alias);
    }

    public static boolean hasDriver(String alias) {
        return getManager().hasDriver(alias);
    }

    public static Object getDriver(String alias) {
        return getManager().getDriver(alias);
    }

    public static Object getDefaultDriver() {
        return getManager().getDefaultDriver();
    }

    public static Object driver() {
        return getManager().driver();
    }

    public static Object driver(String alias) {
        return getManager().driver(alias);
    }

    public static Optional<Storage> disk() {
        return getBean((String) getDefaultDriver());
    }

    public static Optional<Storage> disk(String name) {
        String beanName = hasDriver(name) ? (String) getDriver(name) : name;
        return getBean(beanName);
    }

    public static Optional<Storage> disk(Class<?> clazz) {
        String name = clazz.getName();
        String beanName = hasDriver(name) ? (String) getDriver(name) : name;
        return getBean(beanName);
    }

    public static Optional<Cloud> cloud() {
        return getBean((String) getDefaultDriver());
    }

    public static Optional<Cloud> cloud(String name) {
        String beanName = hasDriver(name) ? (String) getDriver(name) : name;
        return getBean(beanName);
    }

    public static Optional<Cloud> cloud(Class<?> clazz) {
        String name = clazz.getName();
        String beanName = hasDriver(name) ? (String) getDriver(name) : name;
        return getBean(beanName);
    }

    private static <T extends Storage> Optional<T> getBean(String name) {
        Class<?> calazz = RC_Bean.getClazz(name);
        if (calazz == null) {
            return Optional.empty();
        }
        T storage = (T) RC_Bean.getBean(calazz);
        return Optional.of(storage);
    }

}
