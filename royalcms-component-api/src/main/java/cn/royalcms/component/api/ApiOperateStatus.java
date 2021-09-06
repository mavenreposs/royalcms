package cn.royalcms.component.api;

import java.util.Objects;

public class ApiOperateStatus {
    
    private final Integer code;

    private final String message;

    public ApiOperateStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiOperateStatus apiOperateStatus = (ApiOperateStatus) o;
        return code.equals(apiOperateStatus.code) && message.equals(apiOperateStatus.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
