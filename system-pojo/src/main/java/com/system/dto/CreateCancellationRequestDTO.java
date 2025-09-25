// CreateCancellationRequestDTO.java
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
@ApiModel(description = "创建取消申请参数")
public class CreateCancellationRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "预约ID", required = true)
    private String reservationId;
    
    @ApiModelProperty(value = "取消原因", required = true)
    private String reason;
}