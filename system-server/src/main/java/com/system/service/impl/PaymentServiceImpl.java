// PaymentServiceImpl.java
package com.system.service.impl;

import com.system.dto.OfflineTopUpCreateDTO;
import com.system.dto.PaymentNotifyDTO;
import com.system.dto.PaymentQueryDTO;
import com.system.dto.TopUpCreateDTO;
import com.system.entity.Payment;
import com.system.entity.Student;
import com.system.entity.User;
import com.system.mapper.PaymentMapper;
import com.system.mapper.StudentMapper;
import com.system.mapper.UserMapper;
import com.system.service.PaymentService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public BalanceVO getStudentBalance(String studentId) {
        log.info("获取学员账户余额: studentId={}", studentId);
        
        UUID studentUUID = UUID.fromString(studentId);
        
        // 查询学员信息
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        User studentUser = userMapper.selectById(studentUUID);
        
        return BalanceVO.builder()
                .balance(student.getBalance())
                .studentId(UUID.fromString(studentId))
                .studentName(studentUser != null ? studentUser.getName() : "未知")
                .build();
    }

    @Transactional
    @Override
    public TopUpOrderVO createTopUpOrder(TopUpCreateDTO createDTO, String currentUserId) {
        log.info("创建充值订单: studentId={}, amount={}, method={}", 
                createDTO.getStudentId(), createDTO.getAmount(), createDTO.getMethod());
        
        UUID studentUUID = createDTO.getStudentId();
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 验证权限：学员只能为自己充值
        if (!studentUUID.equals(currentUserUUID)) {
            throw new RuntimeException("只能为自己的账户充值");
        }
        
        // 验证学员是否存在
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        // 验证金额
        if (createDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("充值金额必须大于0");
        }
        
        // 生成订单号
        String orderNo = generateOrderNo();
        
        // 创建支付记录
        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .userId(studentUUID)
                .amount(createDTO.getAmount())
                .type("topup")
                .method(createDTO.getMethod())
                .status("pending")
                .orderNo(orderNo)
                .qrCodeUrl(generateQrCodeUrl(orderNo, createDTO.getAmount(), createDTO.getMethod()))
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        paymentMapper.insertPayment(payment);
        
        return TopUpOrderVO.builder()
                .id(payment.getId())
                .orderNo(payment.getOrderNo())
                .studentId(createDTO.getStudentId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .qrCodeUrl(payment.getQrCodeUrl())
                .createdTime(payment.getCreateTime())
                .build();
    }

    @Transactional
    @Override
    public PaymentRecordVO createOfflineTopUp(OfflineTopUpCreateDTO createDTO, String currentUserId) {
        log.info("创建线下充值记录: studentId={}, amount={}", 
                createDTO.getStudentId(), createDTO.getAmount());
        
        UUID studentUUID = createDTO.getStudentId();
        UUID operatorUUID = createDTO.getOperatorId();
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 权限验证：只有管理员可以创建线下充值记录
        User currentUser = userMapper.selectById(currentUserUUID);
        if (currentUser == null || (!"super_admin".equals(currentUser.getRole()) &&
            !"campus_admin".equals(currentUser.getRole()))) {
            throw new RuntimeException("权限不足");
        }
        
        // 验证学员是否存在
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        // 验证操作员是否存在
        User operator = userMapper.selectById(operatorUUID);
        if (operator == null) {
            throw new RuntimeException("操作员不存在");
        }
        
        // 验证金额
        if (createDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("充值金额必须大于0");
        }
        
        // 更新学员余额
        BigDecimal newBalance = student.getBalance().add(createDTO.getAmount());
        studentMapper.updateBalance(studentUUID, newBalance);
        
        // 创建支付记录
        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .userId(studentUUID)
                .amount(createDTO.getAmount())
                .type("topup")
                .method("offline")
                .status("completed")
                .operatorId(operatorUUID)
                .remark(createDTO.getRemark())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        paymentMapper.insertPayment(payment);
        
        return PaymentRecordVO.builder()
                .id(payment.getId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .type(payment.getType())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .createdTime(payment.getCreateTime())
                .build();
    }

    @Override
    public PaymentPageVO getPaymentRecords(PaymentQueryDTO queryDTO) {
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询支付记录
        List<PaymentRecordVO> payments = paymentMapper.selectPayment(queryDTO.getStudentId(), queryDTO, offset, limit);
        
        // 查询总数
        long total = paymentMapper.countPayments(queryDTO.getStudentId(), queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return PaymentPageVO.builder()
                .payments(payments)
                .pagination(pagination)
                .build();
    }

    @Override
    public PaymentRecordDetailVO getPaymentRecordDetail(String paymentId, String currentUserId) {
        log.info("获取支付记录详情: paymentId={}, currentUserId={}", paymentId, currentUserId);
        
        UUID paymentUUID = UUID.fromString(paymentId);
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 查询支付记录详情
        PaymentRecordDetailVO paymentDetail = paymentMapper.selectPaymentDetail(paymentUUID);
        if (paymentDetail == null) {
            throw new RuntimeException("支付记录不存在");
        }
        
        // 权限验证：用户只能查看自己的支付记录，管理员可以查看所有
        if (!paymentDetail.getUserId().equals(currentUserId)) {
            User currentUser = userMapper.selectById(currentUserUUID);
            if (currentUser == null || (!"super_admin".equals(currentUser.getRole()) &&
                !"campus_admin".equals(currentUser.getRole()))) {
                throw new RuntimeException("无权查看此支付记录");
            }
        }
        
        return paymentDetail;
    }

    @Transactional
    @Override
    public String handlePaymentNotification(String gateway, Object notifyParams) {
        log.info("处理支付回调通知: gateway={}", gateway);
        
        try {
            // 解析回调参数
            PaymentNotifyDTO notifyDTO = parseNotifyParams(gateway, notifyParams);
            
            // 验证签名（实际项目中需要实现）
            if (!verifySignature(notifyDTO)) {
                log.error("签名验证失败: gateway={}", gateway);
                return "FAIL";
            }
            
            // 根据订单号查询支付记录
            Payment payment = paymentMapper.selectByOrderNo(notifyDTO.getOutTradeNo());
            if (payment == null) {
                log.error("支付记录不存在: orderNo={}", notifyDTO.getOutTradeNo());
                return "FAIL";
            }
            
            // 检查订单状态，避免重复处理
            if ("completed".equals(payment.getStatus())) {
                log.info("订单已处理完成: orderNo={}", notifyDTO.getOutTradeNo());
                return "SUCCESS";
            }
            
            // 处理支付结果
            if (isPaymentSuccess(notifyDTO)) {
                // 更新支付状态
                paymentMapper.updatePaymentStatus(payment.getId(), "completed",
                        String.valueOf(notifyDTO.getTransactionId()));
                
                // 更新学员余额
                studentMapper.addBalance(payment.getUserId(), payment.getAmount());
                
                log.info("支付成功: orderNo={}, amount={}", notifyDTO.getOutTradeNo(), payment.getAmount());
            } else {
                // 支付失败
                paymentMapper.updatePaymentStatus(payment.getId(), "failed", null);
                log.warn("支付失败: orderNo={}", notifyDTO.getOutTradeNo());
            }
            
            return "SUCCESS";
        } catch (Exception e) {
            log.error("处理支付回调异常: {}", e.getMessage(), e);
            return "FAIL";
        }
    }

    @Override
    public void processTimeoutPayments() {
        log.info("处理超时支付订单");
        
        // 查询超时的待支付订单（例如30分钟前的订单）
        List<Payment> timeoutPayments = paymentMapper.selectPendingPayments(30);
        
        for (Payment payment : timeoutPayments) {
            try {
                paymentMapper.updatePaymentStatus(payment.getId(), "failed", null);
                log.info("标记超时订单为失败: orderNo={}", payment.getOrderNo());
            } catch (Exception e) {
                log.error("处理超时订单异常: orderNo={}, error={}", payment.getOrderNo(), e.getMessage());
            }
        }
    }
    
    // 辅助方法
    private String generateOrderNo() {
        return "TOP" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) 
                + String.format("%04d", (int)(Math.random() * 10000));
    }
    
    private String generateQrCodeUrl(String orderNo, BigDecimal amount, String method) {
        // 实际项目中需要调用支付网关生成二维码
        // 这里返回模拟URL
        return "https://api.example.com/qrcode/" + orderNo;
    }
    
    private PaymentNotifyDTO parseNotifyParams(String gateway, Object notifyParams) {
        // 实际项目中需要根据不同的支付网关解析参数
        // 这里返回模拟数据
        return PaymentNotifyDTO.builder()
                .gateway(gateway)
                .returnCode("SUCCESS")
                .resultCode("SUCCESS")
                .outTradeNo("TOP20231101123456")
                .transactionId(UUID.fromString("4200001234567890"))
                .totalFee(20000)
                .build();
    }
    
    private boolean verifySignature(PaymentNotifyDTO notifyDTO) {
        // 实际项目中需要验证签名
        return true;
    }
    
    private boolean isPaymentSuccess(PaymentNotifyDTO notifyDTO) {
        if ("wechat".equals(notifyDTO.getGateway())) {
            return "SUCCESS".equals(notifyDTO.getReturnCode()) && 
                   "SUCCESS".equals(notifyDTO.getResultCode());
        } else if ("alipay".equals(notifyDTO.getGateway())) {
            return "TRADE_SUCCESS".equals(notifyDTO.getTradeStatus());
        }
        return false;
    }
}