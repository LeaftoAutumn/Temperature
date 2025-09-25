// SystemStatusVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "系统状态信息")
public class SystemStatusVO implements Serializable {
    
    @ApiModelProperty(value = "系统版本")
    private String systemVersion;
    
    @ApiModelProperty(value = "许可证状态")
    private String licenseStatus;
    
    @ApiModelProperty(value = "许可证过期时间")
    private LocalDateTime licenseExpiresAt;
    
    @ApiModelProperty(value = "总用户数")
    private Integer totalUsers;
    
    @ApiModelProperty(value = "学员数量")
    private Integer totalStudents;
    
    @ApiModelProperty(value = "教练数量")
    private Integer totalCoaches;
    
    @ApiModelProperty(value = "今日活跃课程")
    private Integer activeCoursesToday;
    
    @ApiModelProperty(value = "待处理预约")
    private Integer pendingReservations;
    
    @ApiModelProperty(value = "未处理通知")
    private Integer unprocessedNotifications;
    
    @ApiModelProperty(value = "服务器时间")
    private LocalDateTime serverTime;
    
    @ApiModelProperty(value = "系统运行时间")
    private String uptime;
}