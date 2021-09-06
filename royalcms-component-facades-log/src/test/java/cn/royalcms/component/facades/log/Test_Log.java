package cn.royalcms.component.facades.log;

import cn.royalcms.component.facades.RC_Log;
import org.junit.jupiter.api.Test;

public class Test_Log {

    @Test
    public void test1() {
        //输出Json字符串
        RC_Log.json("{\"id\":221,\"name\":\"my name is dsc\",\"desc\":\"this is description!\"}");
        //输出Xml字符串
        RC_Log.xml("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><title>this is a title</title><body>这个是网页</body></html>");

        //创建Java对象
        User user = new User();
        user.id = 102;
        user.name = "DSC_LOG";
        user.age = 22;
        //输出对象
        RC_Log.object(user);
    }

    @Test
    public void test2() {
        RC_Log.info("第一行日志\n换行输出日志");

        try {
            Object obj = null;
            obj.toString();
        } catch (Exception e) {
            RC_Log.error(e, "空指针异常");
        }
    }


    private static class User {

        public Integer id;

        public String name;

        public Integer age;

    }
}
