package cn.royalcms.component.config.proxy;

import cn.royalcms.facades.bean.SpringBeanFactory;
import cn.royalcms.component.config.contracts.ConfigItemRepositoryInterface;
import cn.royalcms.component.config.manager.ItemManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ConfigInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这里的动态调用实现了 php 的 __call 方法");

        System.out.println("method : " + method.getName());
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args["+ i +"] : " + args[i]);
            }
        }

        ConfigItemRepositoryInterface configItemRepository = SpringBeanFactory.getBean(ConfigItemRepositoryInterface.class);
        ItemManager itemManager = new ItemManager(configItemRepository);

        System.out.println("before method call : " + method.getName());
        Object result = method.invoke(itemManager, args);
        System.out.println("method call result : " + result.toString());
        System.out.println("after method call : " + method.getName());
        return result;
    }

}
