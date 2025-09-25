// CoachServiceImpl.java
package com.system.service.impl;

import com.system.dto.CoachQueryDTO;
import com.system.entity.Coach;
import com.system.entity.User;
import com.system.mapper.CoachMapper;
import com.system.mapper.UserMapper;
import com.system.service.CoachService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CoachPageVO getCoaches(CoachQueryDTO queryDTO) {
        log.info("查询教练列表: campusId={}", queryDTO.getCampusId());
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询教练列表
        List<CoachListItemVO> coaches = coachMapper.selectCoaches(queryDTO, offset, limit);

        coaches.forEach(coach -> coach.setAge(calculateAge(coach.getBirthDate())));
        
        // 查询总数
        long total = coachMapper.countCoaches(queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return CoachPageVO.builder()
                .coaches(coaches)
                .pagination(pagination)
                .build();
    }

    @Override
    public CoachDetailVO getCoachDetail(String coachId) {
        log.info("获取教练详情: coachId={}", coachId);
        
        UUID coachUUID = UUID.fromString(coachId);
        CoachDetailVO coachDetail = coachMapper.selectCoachDetail(coachUUID);
        
        if (coachDetail == null) {
            throw new RuntimeException("教练不存在或未通过审核");
        }

        coachDetail.setAge(calculateAge(coachDetail.getBirthDate()));

        // 计算当前学员数量
        int currentStudents = coachMapper.countCurrentStudents(coachUUID);
        coachDetail.setCurrentStudents(currentStudents);
        
        return coachDetail;
    }

    @Override
    public List<CoachListItemVO> getStudentCoaches(String studentId, String currentUserId) {
        log.info("获取学员的已匹配教练列表: studentId={}, currentUserId={}", studentId, currentUserId);
        
        UUID studentUUID = UUID.fromString(studentId);
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 权限验证：学员只能查看自己的教练，管理员可以查看所有
        if (!studentUUID.equals(currentUserUUID)) {
            User currentUser = userMapper.selectById(currentUserUUID);
            if (currentUser == null || (!"super_admin".equals(currentUser.getRole()) &&
                !"campus_admin".equals(currentUser.getRole()))) {
                throw new RuntimeException("无权查看他人的教练列表");
            }
        }
        
        // 验证学员是否存在
        User student = userMapper.selectById(studentUUID);
        if (student == null || !"student".equals(student.getRole())) {
            throw new RuntimeException("学员不存在");
        }

        List<CoachListItemVO> coaches = coachMapper.selectStudentCoaches(studentUUID);

        coaches.forEach(coach -> coach.setAge(calculateAge(coach.getBirthDate())));
        return coaches;
    }

    @Override
    public List<StudentListItemVO> getCoachStudents(String coachId, String currentUserId) {
        log.info("获取教练的已接收学员列表: coachId={}, currentUserId={}", coachId, currentUserId);
        
        UUID coachUUID = UUID.fromString(coachId);
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 权限验证：教练只能查看自己的学员，管理员可以查看所有
        if (!coachUUID.equals(currentUserUUID)) {
            User currentUser = userMapper.selectById(currentUserUUID);
            if (currentUser == null || (!"super_admin".equals(currentUser.getRole()) &&
                !"campus_admin".equals(currentUser.getRole()))) {
                throw new RuntimeException("无权查看他人的学员列表");
            }
        }
        
        // 验证教练是否存在
        Coach coach = coachMapper.selectByUserId(coachUUID);
        if (coach == null) {
            throw new RuntimeException("教练不存在");
        }

        List<StudentListItemVO> studentListItemVOS = coachMapper.selectCoachStudents(coachUUID);

        studentListItemVOS.forEach(studentListItemVO -> studentListItemVO.setAge(calculateAge(studentListItemVO.getBirthDate())));
        return studentListItemVOS;
    }

    private Integer calculateAge(LocalDateTime birthDate) {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();
    }
}