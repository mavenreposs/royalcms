package io.github.mavenreposs.royalcms.component.api;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ApiOperateStatusWithData extends ApiOperateStatus {

    private final HashMap<String, Object> data = new LinkedHashMap<>();

    public ApiOperateStatusWithData(Integer code, String message) {
        super(code, message);

        this.data.put("code", this.getCode());
        this.data.put("msg", this.getMessage());
    }

    public ApiOperateStatusWithData addData(String key, Object data) {
        this.data.put(key, data);
        return this;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

}
