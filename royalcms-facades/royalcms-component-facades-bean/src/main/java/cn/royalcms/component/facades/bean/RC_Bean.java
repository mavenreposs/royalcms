package cn.royalcms.component.facades.bean;


import cn.royalcms.component.bean.SpringBeanFactory;

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
