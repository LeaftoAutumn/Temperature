// Payment.java
package com.system.entity;

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
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private UUID relatedId;
    private BigDecimal amount;
    private String type; // topup, course, tournament, refund
    private String method; // wechat, alipay, offline
    private String status; // pending, completed, failed
    private String orderNo;
    private String qrCodeUrl;
    private String transactionId;
    private UUID operatorId;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}