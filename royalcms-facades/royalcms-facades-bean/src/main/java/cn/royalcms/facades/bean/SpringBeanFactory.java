package cn.royalcms.facades.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 实现ApplicationContextAware接口，并加入Component注解，让spring扫描到该bean
 * 该类用于在普通Java类中注入bean,普通Java类中用@Autowired是无法注入bean的
 */
@Configuration
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanFactory.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * 
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 类名
     * @return 实例
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类的类型
     * @param <T> 类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
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
        return applicationContext.getBean(name, clazz);
    }

}
