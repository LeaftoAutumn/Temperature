// PaymentNotifyDTO.java
package com.system.dto;

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
public class PaymentNotifyDTO implements Serializable {
    
    // 微信支付回调参数
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String outTradeNo;
    private UUID transactionId;
    private Integer totalFee;
    
    // 支付宝支付回调参数
    private String notifyType;
    private UUID notifyId;
    private String tradeNo;
    private String tradeStatus;
    private BigDecimal totalAmount;
    
    // 通用参数
    private String gateway; // wechat, alipay
}