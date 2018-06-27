package com.als.authorize.sso.controller;

import com.als.gateway.message.common.bean.ApiException;
import com.als.gateway.message.common.bean.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiResult onApiException(ApiException e) {
        log.warn("业务处理异常:", e);
        return new ApiResult<>(500, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult onException(Exception e) {
        log.error("系统异常:", e);
        return new ApiResult(500);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("缺少参数", e);
        return new ApiResult(501, "缺少参数: " + e.getParameterName());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ApiResult onTypeMismatchException(TypeMismatchException e) {
        log.info("参数类型错误", e);
        return new ApiResult(502, "参数类型错误: " + e.getPropertyName());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult onHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("不支持的请求类型", e);
        return new ApiResult(504, "不支持的请求类型: " + e.getMethod());
    }
}
