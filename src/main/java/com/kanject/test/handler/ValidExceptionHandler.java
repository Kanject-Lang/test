package com.kanject.test.handler;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/08/22 11:45:55
 */
@Slf4j
@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public Map<String, Object> handle(WebExchangeBindException exception) {
        //获取参数校验错误集合
        List<FieldError> fieldErrors = exception.getFieldErrors();
        //格式化以提供友好的错误提示
        String data = String.format("参数校验错误（%s）：%s", fieldErrors.size(),
                fieldErrors.stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(";")));
        //参数校验失败响应失败个数及原因
        return ImmutableMap.of("code", exception.getStatus().value(),
                "message", exception.getStatus(),
                "data", data);
    }
}
