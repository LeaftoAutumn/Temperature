// PaymentQueryDTO.java
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
@ApiModel(description = "支付记录查询参数")
public class PaymentQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "支付类型")
    private String type; // topup, course, tournament, refund
    
    @ApiModelProperty(value = "支付方式")
    private String method; // wechat, alipay, offline
    
    @ApiModelProperty(value = "支付状态")
    private String status; // pending, completed, failed
    
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;
    
    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
}