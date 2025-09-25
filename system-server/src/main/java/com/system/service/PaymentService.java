// PaymentService.java
package com.system.service;

import com.system.dto.OfflineTopUpCreateDTO;
import com.system.dto.PaymentQueryDTO;
import com.system.dto.TopUpCreateDTO;
import com.system.vo.*;

import java.math.BigDecimal;

public interface PaymentService {
    
    BalanceVO getStudentBalance(String studentId);
    
    TopUpOrderVO createTopUpOrder(TopUpCreateDTO createDTO, String currentUserId);
    
    PaymentRecordVO createOfflineTopUp(OfflineTopUpCreateDTO createDTO, String currentUserId);
    
    PaymentPageVO getPaymentRecords(PaymentQueryDTO queryDTO);
    
    PaymentRecordDetailVO getPaymentRecordDetail(String paymentId, String currentUserId);
    
    String handlePaymentNotification(String gateway, Object notifyParams);
    
    void processTimeoutPayments();
}