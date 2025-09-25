// TournamentRegistrationServiceImpl.java
package com.system.service.impl;

import com.system.dto.TournamentRegistrationDTO;
import com.system.entity.Payment;
import com.system.entity.Student;
import com.system.entity.Tournament;
import com.system.entity.TournamentRegistration;
import com.system.mapper.PaymentMapper;
import com.system.mapper.StudentMapper;
import com.system.mapper.TournamentMapper;
import com.system.mapper.TournamentRegistrationMapper;
import com.system.service.TournamentRegistrationService;
import com.system.vo.TournamentRegistrationResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class TournamentRegistrationServiceImpl implements TournamentRegistrationService {

    @Autowired
    private TournamentRegistrationMapper registrationMapper;

    @Autowired
    private TournamentMapper tournamentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional
    @Override
    public TournamentRegistrationResponseVO registerForTournament(TournamentRegistrationDTO registrationDTO, 
                                                                 String currentUserId) {
        log.info("月赛报名: tournamentId={}, userId={}", registrationDTO.getTournamentId(), currentUserId);
        
        UUID tournamentUUID = UUID.fromString(registrationDTO.getTournamentId());
        UUID studentUUID = UUID.fromString(currentUserId);
        
        // 验证月赛是否存在
        Tournament tournament = tournamentMapper.selectById(tournamentUUID);
        if (tournament == null) {
            throw new RuntimeException("月赛不存在");
        }
        
        // 验证月赛状态
        if (!"upcoming".equals(tournament.getStatus())) {
            throw new RuntimeException("月赛已过期或已完成，无法报名");
        }
        
        // 验证学员是否存在
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        // 检查是否已报名
        TournamentRegistration existingRegistration = registrationMapper.selectByStudentAndTournament(
            studentUUID, tournamentUUID);
        if (existingRegistration != null) {
            throw new RuntimeException("您已报名该月赛");
        }
        
        // 验证余额是否足够
        if (student.getBalance().compareTo(tournament.getEntryFee()) < 0) {
            throw new RuntimeException("余额不足，请先充值");
        }
        
        // 扣款
        BigDecimal newBalance = student.getBalance().subtract(tournament.getEntryFee());
        studentMapper.updateBalance(studentUUID, newBalance);
        
        // 创建支付记录
        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .userId(studentUUID)
                .relatedId(tournamentUUID)
                .amount(tournament.getEntryFee())
                .type("tournament")
                .method("system")
                .status("completed")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        paymentMapper.insertPayment(payment);
        
        // 创建报名记录
        TournamentRegistration registration = TournamentRegistration.builder()
                .id(UUID.randomUUID())
                .studentId(studentUUID)
                .tournamentId(tournamentUUID)
                .feePaid(true)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        registrationMapper.insertRegistration(registration);
        
        return TournamentRegistrationResponseVO.builder()
                .id(registration.getId().toString())
                .message("报名成功")
                .build();
    }
}