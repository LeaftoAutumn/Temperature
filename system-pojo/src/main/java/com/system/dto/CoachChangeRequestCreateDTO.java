// CoachChangeRequestCreateDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "更换教练请求参数")
public class CoachChangeRequestCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private UUID studentId;
    
    @ApiModelProperty(value = "当前教练ID", required = true)
    private UUID currentCoachId;
    
    @ApiModelProperty(value = "新教练ID", required = true)
    private UUID newCoachId;
    
    @ApiModelProperty(value = "更换原因")
    private String reason;
}