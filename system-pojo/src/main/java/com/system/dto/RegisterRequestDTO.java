// RegisterRequestDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "注册请求参数")
public class RegisterRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "用户名", required = true, example = "lisi")
    private String username;
    
    @ApiModelProperty(value = "密码", required = true, example = "MyPass123!")
    private String password;
    
    @ApiModelProperty(value = "真实姓名", required = true, example = "李四")
    private String name;
    
    @ApiModelProperty(value = "性别", example = "male")
    private String gender;
    
    @ApiModelProperty(value = "出生日期", example = "2000-01-01")
    private LocalDate birthDate;
    
    @ApiModelProperty(value = "手机号", required = true, example = "13800138000")
    private String phone;
    
    @ApiModelProperty(value = "邮箱", example = "lisi@example.com")
    private String email;
    
    @ApiModelProperty(value = "校区ID", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID campusId;
    
    @ApiModelProperty(value = "角色", required = true, example = "student")
    private String user_type;
    
    @ApiModelProperty(value = "教练等级", example = "intermediate")
    private String level;
    
    @ApiModelProperty(value = "教练照片URL", example = "/uploads/coaches/photo1.jpg")
    private String photoUrl;
    
    @ApiModelProperty(value = "获奖记录", example = "全市乒乓球比赛冠军")
    private String awards;
}