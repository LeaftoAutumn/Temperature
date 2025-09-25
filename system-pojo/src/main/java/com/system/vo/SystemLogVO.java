// SystemLogVO.java
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
@ApiModel(description = "系统日志信息")
public class SystemLogVO implements Serializable {
    
    @ApiModelProperty(value = "日志ID")
    private UUID id;
    
    @ApiModelProperty(value = "用户ID")
    private UUID userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "用户角色")
    private String userRole;
    
    @ApiModelProperty(value = "操作描述")
    private String action;
    
    @ApiModelProperty(value = "操作类型")
    private String actionType;
    
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;
    
    @ApiModelProperty(value = "用户代理")
    private String userAgent;
    
    @ApiModelProperty(value = "操作详情")
    private Object details;
    
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createTime;
}