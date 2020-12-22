package com.kanject.test.result;

import lombok.Data;

/**
 * @description: TODO
 * @author: Kanject
 */
@Data
public class TestResult<T> {
    private Integer code;
    private String message;
    private T data;

    public TestResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> TestResult ok(T data) {
        return new TestResult(200, "OK", data);
    }

    public static <T> TestResult build(Integer code, String message, T data) {
        return new TestResult(code, message, data);
    }
}
