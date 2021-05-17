package io.github.mavenreposs.royalcms.api;

import io.github.mavenreposs.royalcms.facades.RC_Time;

public class ApiSuccessResponse implements ApiResponseInterface {

    private final String status;

    private final Object data;

    private final String time;

    public ApiSuccessResponse(Object data) {
        this.status = "success";
        this.data = data;
        this.time = RC_Time.date("yyyy-MM-dd HH:mm:ss");
    }

    public static ApiResponseInterface of(Object data) {
        return new ApiSuccessResponse(data);
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public String getTime() {
        return time;
    }
}
