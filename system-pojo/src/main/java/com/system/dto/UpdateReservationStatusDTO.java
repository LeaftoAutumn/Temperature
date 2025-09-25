// UpdateReservationStatusDTO.java
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
@ApiModel(description = "更新预约状态参数")
public class UpdateReservationStatusDTO implements Serializable {
    
    @ApiModelProperty(value = "状态", required = true, example = "confirmed")
    private String status;
}