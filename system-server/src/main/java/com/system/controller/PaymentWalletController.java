// PaymentWalletController.java
package com.system.controller;

import com.system.dto.OfflineTopUpCreateDTO;
import com.system.dto.PaymentQueryDTO;
import com.system.dto.TopUpCreateDTO;
import com.system.service.PaymentService;
import com.system.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "支付与账户管理")
public class PaymentWalletController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/students/{studentId}/balance")
    @ApiOperation("获取学员账户余额")
    public BalanceVO getStudentBalance(@PathVariable String studentId,
                                     HttpServletRequest request) {
        /*String currentUserId = (String) request.getAttribute("userId");
        log.info("获取学员账户余额: studentId={}, currentUserId={}", studentId, currentUserId);
        return paymentService.getStudentBalance(studentId, currentUserId);*/
        log.info("获取学员账户余额: studentId={}", studentId);
        return paymentService.getStudentBalance(studentId);
    }

    @PostMapping("/top-ups")
    @ApiOperation("发起充值订单")
    @ResponseStatus(HttpStatus.CREATED)
    public TopUpOrderVO createTopUpOrder(@Valid @RequestBody TopUpCreateDTO createDTO,
                                       HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("发起充值订单: currentUserId={}", currentUserId);
        return paymentService.createTopUpOrder(createDTO, currentUserId);
    }

    @PostMapping("/payments/notify/{gateway}")
    @ApiOperation("支付回调通知")
    public String handlePaymentNotification(@PathVariable String gateway,
                                          @RequestBody(required = false) Map<String, Object> notifyParams,
                                          HttpServletRequest request) {
        log.info("支付回调通知: gateway={}", gateway);
        return paymentService.handlePaymentNotification(gateway, notifyParams);
    }

    @PostMapping("/offline-top-ups")
    @ApiOperation("录入线下充值记录")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRecordVO createOfflineTopUp(@Valid @RequestBody OfflineTopUpCreateDTO createDTO,
                                            HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("录入线下充值记录: currentUserId={}", currentUserId);
        return paymentService.createOfflineTopUp(createDTO, currentUserId);
    }

    @GetMapping("/payments")
    @ApiOperation("获取支付记录列表")
    public PaymentPageVO getPaymentRecords(@Valid PaymentQueryDTO queryDTO,
                                         HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取支付记录列表: currentUserId={}", currentUserId);
        return paymentService.getPaymentRecords(queryDTO, currentUserId);
    }

    @GetMapping("/payments/{paymentId}")
    @ApiOperation("获取支付记录详情")
    public PaymentRecordDetailVO getPaymentRecordDetail(@PathVariable String paymentId,
                                                      HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取支付记录详情: paymentId={}, currentUserId={}", paymentId, currentUserId);
        return paymentService.getPaymentRecordDetail(paymentId, currentUserId);
    }
}