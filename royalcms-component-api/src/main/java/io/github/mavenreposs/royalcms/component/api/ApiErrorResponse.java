package io.github.mavenreposs.royalcms.component.api;

public class ApiErrorResponse implements ApiResponseInterface {

    private final String status;

    private final ApiError errors;

    public ApiErrorResponse(Integer code, String message) {
        this.status = "failed";
        this.errors = new ApiError(code, message);
    }

    public ApiErrorResponse(ApiError errors) {
        this.status = "failed";
        this.errors = errors;
    }

    public static ApiErrorResponse of(Integer code, String message) {
        return new ApiErrorResponse(code, message);
    }

    public static ApiErrorResponse of(ApiError errors) {
        return new ApiErrorResponse(errors);
    }


    public String getStatus() {
        return status;
    }

    public ApiError getErrors() {
        return errors;
    }


}
