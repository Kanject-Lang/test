package com.kanject.test.handler;

import com.google.common.collect.ImmutableMap;
import com.kanject.test.result.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/08/22 11:45:55
 */
@Slf4j
@RestControllerAdvice
public class GlobalValidExceptionHandler {

    /**
     * 处理 form data方式调用接口校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public TestResult bindExceptionHandler(BindException exception) {
        log.info("处理form data方式调用接口校验失败抛出的异常");
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errorStrs = fieldErrors.stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        return TestResult.build(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorStrs);
    }

    /**
     * 处理 json 请求体调用接口校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public TestResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        log.info("处理json 请求体调用接口校验失败抛出的异常");
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errorStrs = fieldErrors.stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        return TestResult.build(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorStrs);
    }

    /**
     * 处理单个参数校验失败抛出的异常(需要在Controller上加@Validated注解)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public TestResult constraintViolationExceptionHandler(ConstraintViolationException exception) {
        log.info("处理单个参数校验失败抛出的异常");
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        List<String> errorStrs = constraintViolations.stream().map(cv -> cv.getMessage()).collect(Collectors.toList());
        return TestResult.build(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorStrs);
    }
}
