// OfflineTopUpCreateDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "线下充值请求参数")
public class OfflineTopUpCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private String studentId;
    
    @ApiModelProperty(value = "充值金额", required = true)
    private BigDecimal amount;
    
    @ApiModelProperty(value = "操作员ID", required = true)
    private String operatorId;
    
    @ApiModelProperty(value = "备注信息")
    private String remark;
}