package cn.royalcms.facades.bean;

import org.springframework.util.ClassUtils;

public class RC_Bean {

    private RC_Bean() {
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 类名
     * @return 实例
     */
    public static Object getBean(String name) {
        return SpringBeanFactory.getBean(name);
    }

    public static Class<?> getClazz(String name) {
        try {
            return ClassUtils.forName(name, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类的类型
     * @param <T> 类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return SpringBeanFactory.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name 类名
     * @param clazz 类的类型
     * @param <T> 类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringBeanFactory.getBean(name, clazz);
    }


}
