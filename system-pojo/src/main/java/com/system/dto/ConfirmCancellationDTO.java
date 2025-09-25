// ConfirmCancellationDTO.java
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
@ApiModel(description = "确认取消参数")
public class ConfirmCancellationDTO implements Serializable {
    
    @ApiModelProperty(value = "是否确认", required = true)
    private Boolean confirm;
}