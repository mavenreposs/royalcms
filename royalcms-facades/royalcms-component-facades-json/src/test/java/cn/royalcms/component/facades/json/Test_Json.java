package cn.royalcms.component.facades.json;

import cn.royalcms.component.facades.log.RC_Log;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test_Json {

    @Test
    public void testDecode()
    {
        String json = "{\"name\":\"XiaoQiang\",\"email\":\"10000@qq.com\",\"age\":26,\"isDeveloper\":true}";
        String json2 = "[{\"name\":\"XiaoQiang\",\"email\":\"10000@qq.com\",\"age\":26,\"isDeveloper\":true},{\"name\":\"XiaoQiang\",\"email\":\"10000@qq.com\",\"age\":26,\"isDeveloper\":true}]";

        Object a = RC_Json.decode(json);
        List<Object> b = RC_Json.decodeAsList(json2);
        Map<String, Object> c = RC_Json.decodeAsMap(json);
        User d = RC_Json.decode(json, User.class);

        Type type = new TypeToken<List<User>>() {}.getType();
        List<User> e = RC_Json.decode(json2, type);

        RC_Log.info(json2);

        RC_Log.object(a);
        RC_Log.object(b);
        RC_Log.object(b.get(1));
        RC_Log.object(c);
        RC_Log.info(c.get("name").toString());
        RC_Log.info(d.name);
        RC_Log.info(d.getEmail());
        RC_Log.object(e);
    }

    @Test
    public void testEncode()
    {
        User user = new User(
                "XiaoQiang",
                "10000@qq.com",
                26,
                true
        );

        String json = RC_Json.encode(user);
        String json2 = RC_Json.encode(Arrays.asList(user, user));

        RC_Log.info(json);
        RC_Log.info(json2);

    }

    private static class User {
        private String name;
        private String email;
        private Integer age;
        private boolean isDeveloper;

        public User(String name, String email, Integer age, boolean isDeveloper) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.isDeveloper = isDeveloper;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public boolean isDeveloper() {
            return isDeveloper;
        }

        public void setDeveloper(boolean developer) {
            isDeveloper = developer;
        }
    }

}
