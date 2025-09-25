// LoginResponseVO.java
package com.system.vo;

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
@ApiModel(description = "登录响应数据")
public class LoginResponseVO implements Serializable {
    
    @ApiModelProperty(value = "JWT Token")
    private String token;
    
    @ApiModelProperty(value = "用户信息")
    private UserVO user;
}