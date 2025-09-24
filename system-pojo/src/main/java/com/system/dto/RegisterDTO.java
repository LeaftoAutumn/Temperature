package com.system.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "注册时传递的数据模型")
public class RegisterDTO {

    private String username;

    private String password;

    private String name;

    private String gender;

    private LocalDateTime birthday;

    private String phoneNumber;

    private String email;

    private String campusId;

    private String role;
}
