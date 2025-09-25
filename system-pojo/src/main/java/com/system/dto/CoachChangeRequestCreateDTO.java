// CoachChangeRequestCreateDTO.java
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
@ApiModel(description = "更换教练请求参数")
public class CoachChangeRequestCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private String studentId;
    
    @ApiModelProperty(value = "当前教练ID", required = true)
    private String currentCoachId;
    
    @ApiModelProperty(value = "新教练ID", required = true)
    private String newCoachId;
    
    @ApiModelProperty(value = "更换原因")
    private String reason;
}