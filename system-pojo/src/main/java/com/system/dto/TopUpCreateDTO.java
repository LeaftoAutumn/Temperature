// TopUpCreateDTO.java
package com.system.dto;

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
@ApiModel(description = "充值请求参数")
public class TopUpCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private UUID studentId;
    
    @ApiModelProperty(value = "充值金额", required = true)
    private BigDecimal amount;
    
    @ApiModelProperty(value = "支付方式", required = true)
    private String method; // wechat, alipay
}