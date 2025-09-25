// BalanceVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "账户余额信息")
public class BalanceVO implements Serializable {
    
    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;
    
    @ApiModelProperty(value = "学员ID")
    private UUID studentId;
    
    @ApiModelProperty(value = "学员姓名")
    private String studentName;
}