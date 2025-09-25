// CoachChangeServiceImpl.java
package com.system.service.impl;

import com.system.dto.CoachChangeRequestCreateDTO;
import com.system.entity.Coach;
import com.system.entity.CoachChangeRequest;
import com.system.entity.CoachStudentMatch;
import com.system.entity.Student;
import com.system.mapper.CoachChangeRequestMapper;
import com.system.mapper.CoachMapper;
import com.system.mapper.CoachStudentMatchMapper;
import com.system.mapper.StudentMapper;
import com.system.service.CoachChangeService;
import com.system.vo.CoachChangeRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class CoachChangeServiceImpl implements CoachChangeService {

    @Autowired
    private CoachChangeRequestMapper changeRequestMapper;

    @Autowired
    private CoachStudentMatchMapper matchMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Transactional
    @Override
    public CoachChangeRequestVO createCoachChangeRequest(CoachChangeRequestCreateDTO createDTO, String currentUserId) {
        log.info("发起更换教练请求: studentId={}, currentCoachId={}, newCoachId={}", 
                createDTO.getStudentId(), createDTO.getCurrentCoachId(), createDTO.getNewCoachId());
        
        UUID studentUUID = createDTO.getStudentId();
        UUID currentCoachUUID = createDTO.getCurrentCoachId();
        UUID newCoachUUID = createDTO.getNewCoachId();
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 验证权限：学员只能为自己发起更换请求
        if (!studentUUID.equals(currentUserUUID)) {
            throw new RuntimeException("只能为自己发起更换教练请求");
        }
        
        // 验证学员是否存在
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        // 验证当前教练关系是否存在
        CoachStudentMatch currentMatch = matchMapper.selectByStudentAndCoach(studentUUID, currentCoachUUID);
        if (currentMatch == null || !"accepted".equals(currentMatch.getStatus())) {
            throw new RuntimeException("学员与当前教练未建立匹配关系");
        }
        
        // 验证新教练是否存在且已通过审核
        Coach newCoach = coachMapper.selectByUserId(newCoachUUID);
        if (newCoach == null || !newCoach.getIsApproved()) {
            throw new RuntimeException("新教练不存在或未通过审核");
        }
        
        // 验证学员与新教练是否已建立双选关系
        CoachStudentMatch newMatch = matchMapper.selectByStudentAndCoach(studentUUID, newCoachUUID);
        if (newMatch == null || !"accepted".equals(newMatch.getStatus())) {
            throw new RuntimeException("学员与新教练未建立双选关系");
        }
        
        // 检查新教练是否已达学员上限
        int currentStudents = matchMapper.countAcceptedMatchesByCoach(newCoachUUID);
        if (currentStudents >= newCoach.getMaxStudents()) {
            throw new RuntimeException("新教练已接收学员数量已达上限");
        }
        
        // 创建更换教练请求
        CoachChangeRequest changeRequest = CoachChangeRequest.builder()
                .id(UUID.randomUUID())
                .studentId(studentUUID)
                .currentCoachId(currentCoachUUID)
                .newCoachId(newCoachUUID)
                .reason(createDTO.getReason())
                .status("pending")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        changeRequestMapper.insertChangeRequest(changeRequest);
        
        return CoachChangeRequestVO.builder()
                .id(changeRequest.getId())
                .studentId(changeRequest.getStudentId())
                .currentCoachId(changeRequest.getCurrentCoachId())
                .newCoachId(changeRequest.getNewCoachId())
                .status(changeRequest.getStatus())
                .reason(changeRequest.getReason())
                .createdTime(changeRequest.getCreateTime())
                .build();
    }
}