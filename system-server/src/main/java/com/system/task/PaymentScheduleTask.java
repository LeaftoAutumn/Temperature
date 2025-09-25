// PaymentScheduleTask.java
package com.system.task;

import com.system.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentScheduleTask {

    @Autowired
    private PaymentService paymentService;

    /**
     * 每5分钟检查一次超时支付订单
     */
    @Scheduled(fixedRate = 300000) // 5分钟
    public void processTimeoutPayments() {
        try {
            paymentService.processTimeoutPayments();
        } catch (Exception e) {
            log.error("处理超时支付订单异常: {}", e.getMessage());
        }
    }
}