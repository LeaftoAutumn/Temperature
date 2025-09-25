// PaymentPageVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "支付记录分页响应")
public class PaymentPageVO implements Serializable {
    
    @ApiModelProperty(value = "支付记录列表")
    private List<PaymentRecordVO> payments;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}