package io.github.mavenreposs.royalcms.api;

public class ApiError extends ApiOperateStatus {
    public ApiError(Integer code, String message) {
        super(code, message);
    }
}
