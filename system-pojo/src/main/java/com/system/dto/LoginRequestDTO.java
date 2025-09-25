// LoginRequestDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录请求参数")
public class LoginRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "用户名", required = true, example = "zhangsan")
    private String username;
    
    @ApiModelProperty(value = "密码", required = true, example = "Passw0rd!")
    private String password;
}