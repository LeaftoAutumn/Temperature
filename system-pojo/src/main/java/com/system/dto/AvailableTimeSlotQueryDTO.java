// AvailableTimeSlotQueryDTO.java
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
@ApiModel(description = "可用时段查询参数")
public class AvailableTimeSlotQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "教练ID", required = true)
    private UUID coachId;
    
    @ApiModelProperty(value = "查询日期", required = true)
    private LocalDate date;
    
    @ApiModelProperty(value = "课程时长", example = "60")
    private Integer duration;
}