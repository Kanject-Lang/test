package com.kanject.test.entity;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/08/22 11:36:45
 */
@Data
public class User {
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Email(message = "Email格式错误")
    private String email;
//    @PastOrPresent(message = "日期小于等于当前时间")
//    private Date birthday;
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$"
            , message = "手机号格式错误")
    private String phone;
    @Min(value = 0, message = "年龄超出范围，最小值为0")
    @Max(value = 120, message = "年龄超出范围，最大值为120")
    private Integer age;
}
