package cn.royalcms.facades.event;

import cn.royalcms.component.bean.SpringBeanFactory;
import org.springframework.context.ApplicationEvent;

public class RC_Event {

    private RC_Event() {
    }

    public static void fire(ApplicationEvent event) {
        event(event);
    }

    public static void event(ApplicationEvent event) {
        SpringBeanFactory.getApplicationContext().publishEvent(event);
    }

}
