// PaymentRecordVO.java
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "支付记录信息")
public class PaymentRecordVO implements Serializable {
    
    @ApiModelProperty(value = "支付记录ID")
    private String id;
    
    @ApiModelProperty(value = "用户ID")
    private String userId;
    
    @ApiModelProperty(value = "关联业务ID")
    private String relatedId;
    
    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;
    
    @ApiModelProperty(value = "支付类型")
    private String type;
    
    @ApiModelProperty(value = "支付方式")
    private String method;
    
    @ApiModelProperty(value = "支付状态")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}