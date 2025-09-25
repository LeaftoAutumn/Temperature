// PaymentMapper.java
package com.system.mapper;

import com.system.dto.PaymentQueryDTO;
import com.system.entity.Payment;
import com.system.vo.PaymentRecordDetailVO;
import com.system.vo.PaymentRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PaymentMapper {

    int insertPayment(Payment payment);
    
    Payment selectById(@Param("id") UUID id);
    
    Payment selectByOrderNo(@Param("orderNo") String orderNo);
    
    int updatePaymentStatus(@Param("id") UUID id, @Param("status") String status, 
                          @Param("transactionId") String transactionId);
    
    List<PaymentRecordVO> selectPayment(@Param("userId") UUID userId,
                                       @Param("queryDTO") PaymentQueryDTO queryDTO,
                                       @Param("offset") long offset,
                                       @Param("limit") int limit);
    
    long countPayments(@Param("userId") UUID userId,
                     @Param("queryDTO") PaymentQueryDTO queryDTO);
    
    PaymentRecordDetailVO selectPaymentDetail(@Param("id") UUID id);
    
    List<Payment> selectPendingPayments(@Param("timeoutMinutes") int timeoutMinutes);
}