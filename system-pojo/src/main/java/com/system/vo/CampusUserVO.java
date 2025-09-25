// CampusUserVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "校区用户信息")
public class CampusUserVO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private UUID id;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "真实姓名")
    private String name;
    
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "角色")
    private String role;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}