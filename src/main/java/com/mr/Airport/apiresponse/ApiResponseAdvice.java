package com.mr.Airport.apiresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, 
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body;
        } else if (body instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) body;
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            HttpStatus status = HttpStatus.resolve(statusCode.value());
            if (status == null) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ApiResponse<>(responseEntity.getBody(), status.getReasonPhrase(), status.value());
        } else {
            return new ApiResponse<>(body, "Success", HttpStatus.OK.value());
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<String> handleException(Exception e) {
        return new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}



