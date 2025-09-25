// ReservationQueryDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "预约查询参数")
public class ReservationQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "状态过滤")
    private String status;
    
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;
    
    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;
    
    @ApiModelProperty(value = "页码", example = "1")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量", example = "20")
    private Integer limit;
}