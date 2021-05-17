package io.github.mavenreposs.royalcms.api;

public class ApiResponse {

    private ApiResponseInterface apiResponse;

    public ApiResponse(ApiResponseInterface apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ApiResponseInterface getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(ApiResponseInterface apiResponse) {
        this.apiResponse = apiResponse;
    }

    public static ApiResponse succeed(Object data) {
        return new ApiResponse(new ApiSuccessResponse(data));
    }

    public static ApiResponse failed(ApiError error) {
        return new ApiResponse(new ApiErrorResponse(error));
    }

    public static ApiResponse failed(Integer code, String message) {
        return new ApiResponse(ApiErrorResponse.of(code, message));
    }

    public static ApiResponse error(ApiError error) {
        return new ApiResponse(new ApiErrorResponse(error));
    }

    public static ApiResponse error(Integer code, String message) {
        return new ApiResponse(ApiErrorResponse.of(code, message));
    }

}
