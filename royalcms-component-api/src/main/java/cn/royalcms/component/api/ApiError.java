package cn.royalcms.component.api;

public class ApiError extends ApiOperateStatus {
    public ApiError(Integer code, String message) {
        super(code, message);
    }
}
