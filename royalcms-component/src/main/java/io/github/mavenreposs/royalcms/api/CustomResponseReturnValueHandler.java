package io.github.mavenreposs.royalcms.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class CustomResponseReturnValueHandler implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public boolean isAsyncReturnValue(Object o, MethodParameter methodParameter) {
        return supportsReturnType(methodParameter);
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getAnnotatedElement().getAnnotation(CustomApiResponse.class) != null;
    }

    @Override
    public void handleReturnValue(Object data, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        assert response != null;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        if (data instanceof ApiError) {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ApiErrorResponse.of((ApiError) data)));
        } else if (data instanceof ApiOperateStatusWithData) {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ApiSuccessResponse.of(((ApiOperateStatusWithData) data).getData())));
        } else if (data instanceof ApiOperateStatus) {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ApiSuccessResponse.of(data)));
        }  else if (data instanceof ApiResponse) {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(((ApiResponse) data).getApiResponse()));
        } else {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ApiSuccessResponse.of(data)));
        }
    }
}
