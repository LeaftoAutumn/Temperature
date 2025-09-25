// CreateReservationRequestDTO.java
package com.system.dto;

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
@ApiModel(description = "创建预约请求参数")
public class CreateReservationRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private String studentId;
    
    @ApiModelProperty(value = "教练ID", required = true)
    private String coachId;
    
    @ApiModelProperty(value = "开始时间", required = true)
    private LocalDateTime startTime;
    
    @ApiModelProperty(value = "结束时间", required = true)
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "球桌编号")
    private Integer tableNumber;
}