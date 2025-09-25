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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "更换教练请求信息")
public class CoachChangeRequestVO implements Serializable {
    
    @ApiModelProperty(value = "请求ID")
    private UUID id;
    
    @ApiModelProperty(value = "学员ID")
    private UUID studentId;
    
    @ApiModelProperty(value = "当前教练ID")
    private UUID currentCoachId;
    
    @ApiModelProperty(value = "新教练ID")
    private UUID newCoachId;
    
    @ApiModelProperty(value = "请求状态")
    private String status;
    
    @ApiModelProperty(value = "更换原因")
    private String reason;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}