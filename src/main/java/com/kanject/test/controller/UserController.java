package com.kanject.test.controller;

import com.kanject.test.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/08/22 10:13:38
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello, World!";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult result) {
        FieldError fieldError = result.getFieldError();
        if (fieldError != null) {
            log.info("PARAM ERROR =====> {}", fieldError);
            return ResponseEntity.ok(fieldError.getDefaultMessage());
        } else {
            log.info("USER ADD SUCCESS ===> {}", user);
            return ResponseEntity.ok(user.toString());
        }
    }
}
