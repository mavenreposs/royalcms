package cn.royalcms.component.jwt;

import io.jsonwebtoken.impl.JwtMap;

import java.util.Map;

public class JwtBody extends JwtMap {

    public JwtBody() {
        super();
    }

    public JwtBody(Map<String, Object> map) {
        super(map);
    }

    public JwtBody setUserId(int userId) {
        setValue("user_id", userId);
        return this;
    }

    public int getUserId() {
        return getInteger("user_id");
    }

    /**
     * 获取整型数据
     * @param name key name
     * @return int
     */
    protected Integer getInteger(String name) {
        Object v = get(name);
        return v != null ? (Integer) v : 0;
    }

}
