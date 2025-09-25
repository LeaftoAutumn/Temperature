// TopUpOrderVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "充值订单信息")
public class TopUpOrderVO implements Serializable {
    
    @ApiModelProperty(value = "订单ID")
    private UUID id;
    
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    
    @ApiModelProperty(value = "学员ID")
    private UUID studentId;
    
    @ApiModelProperty(value = "充值金额")
    private BigDecimal amount;
    
    @ApiModelProperty(value = "支付方式")
    private String method;
    
    @ApiModelProperty(value = "订单状态")
    private String status;
    
    @ApiModelProperty(value = "支付二维码URL")
    private String qrCodeUrl;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}