// SystemActivateDTO.java
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
@ApiModel(description = "系统激活请求参数")
public class SystemActivateDTO implements Serializable {
    
    @ApiModelProperty(value = "系统激活密钥", required = true)
    private String licenseKey;
}