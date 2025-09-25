// CoachChangeRequestVO.java
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
@ApiModel(description = "更换教练请求信息")
public class CoachChangeRequestVO implements Serializable {
    
    @ApiModelProperty(value = "请求ID")
    private String id;
    
    @ApiModelProperty(value = "学员ID")
    private String studentId;
    
    @ApiModelProperty(value = "当前教练ID")
    private String currentCoachId;
    
    @ApiModelProperty(value = "新教练ID")
    private String newCoachId;
    
    @ApiModelProperty(value = "请求状态")
    private String status;
    
    @ApiModelProperty(value = "更换原因")
    private String reason;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}