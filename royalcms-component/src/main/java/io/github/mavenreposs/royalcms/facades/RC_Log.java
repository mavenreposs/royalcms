package io.github.mavenreposs.royalcms.facades;

import io.github.mavenreposs.component.log.RCLog;

public class RC_Log {
    //禁止实例
    private RC_Log() {
        throw new UnsupportedOperationException();
    }

    public static void debug(String message, Object... args) {
        RCLog.debug(message, args);
    }

    public static void error(String message, Object... args) {
        RCLog.error(null, message, args);
    }

    public static void error(Throwable throwable, String message, Object... args) {
        RCLog.error(throwable, message, args);
    }

    public static void info(String message, Object... args) {
        RCLog.info(message, args);
    }

    public static void warn(String message, Object... args) {
        RCLog.warn(message, args);
    }

    public static void json(String json) {
        RCLog.json(json);
    }

    public static void xml(String xml) {
        RCLog.xml(xml);
    }

    public static void object(Object obj) {
        RCLog.object(obj);
    }

    public static void println(Object obj) {
        System.out.println("************** start ****************");
        System.out.println(obj);
        System.out.println("************** end ******************");
    }
}
