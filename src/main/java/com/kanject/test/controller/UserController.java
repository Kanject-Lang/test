package com.kanject.test.controller;

import com.kanject.test.entity.User;
import com.kanject.test.result.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/08/22 10:13:38
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello, World!";
    }

    @PostMapping("/valid")
    public TestResult valid(@Validated @RequestBody User user, BindingResult result) {
        FieldError fieldError = result.getFieldError();
        if (fieldError != null) {
            log.info("PARAM ERROR =====> {}", fieldError);
            return TestResult.build(400, "Bad Request", fieldError.getDefaultMessage());
        } else {
            log.info("USER ADD SUCCESS ===> {}", user);
            return TestResult.ok(user);
        }
    }

    @PostMapping("/valid2")
    public TestResult valid2(@Validated @RequestBody User user, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorStrs = fieldErrors.stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(errorStrs)) {
            log.info("PARAM ERROR =====> {}", errorStrs);
            return TestResult.build(400, "Bad Request", errorStrs);
        } else {
            log.info("USER ADD SUCCESS ===> {}", user);
            return TestResult.ok(user);
        }
    }

    @PostMapping("/valid3")
    public TestResult valid3(@Validated @RequestBody User user) {
            log.info("USER ADD SUCCESS ===> {}", user);
            return TestResult.ok(user);
    }

    @GetMapping("/valid4")
    public TestResult valid4(@Validated User user) {
        log.info("USER ADD SUCCESS ===> {}", user);
        return TestResult.ok(user);
    }

    @GetMapping("/valid5/{username}")
    public TestResult valid5(@PathVariable("username") @Size(max = 10, min = 3, message = "username必须在3-10字符之间") String username) {
        log.info("USER ADD SUCCESS ===> {}", username);
        return TestResult.ok(String.format("username: %s", username));
    }
}
