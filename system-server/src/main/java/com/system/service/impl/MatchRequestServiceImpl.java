// MatchRequestServiceImpl.java
package com.system.service.impl;

import com.system.dto.MatchRequestCreateDTO;
import com.system.dto.MatchRequestQueryDTO;
import com.system.dto.MatchRequestUpdateDTO;
import com.system.entity.Coach;
import com.system.entity.CoachStudentMatch;
import com.system.entity.Student;
import com.system.entity.User;
import com.system.mapper.CoachMapper;
import com.system.mapper.CoachStudentMatchMapper;
import com.system.mapper.StudentMapper;
import com.system.mapper.UserMapper;
import com.system.service.MatchRequestService;
import com.system.vo.MatchRequestDetailVO;
import com.system.vo.MatchRequestPageVO;
import com.system.vo.MatchRequestVO;
import com.system.vo.PaginationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MatchRequestServiceImpl implements MatchRequestService {

    @Autowired
    private CoachStudentMatchMapper matchMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Transactional
    @Override
    public MatchRequestVO createMatchRequest(MatchRequestCreateDTO createDTO, String currentUserId) {
        log.info("发送双选申请: studentId={}, coachId={}", createDTO.getStudentId(), createDTO.getCoachId());
        
        UUID studentUUID = UUID.fromString(createDTO.getStudentId());
        UUID coachUUID = UUID.fromString(createDTO.getCoachId());
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 验证权限：学员只能为自己发送申请
        if (!studentUUID.equals(currentUserUUID)) {
            throw new RuntimeException("只能为自己发送双选申请");
        }
        
        // 验证学员是否存在
        Student student = studentMapper.selectByUserId(studentUUID);
        if (student == null) {
            throw new RuntimeException("学员不存在");
        }
        
        // 验证教练是否存在且已通过审核
        Coach coach = coachMapper.selectByUserId(coachUUID);
        if (coach == null || !coach.getIsApproved()) {
            throw new RuntimeException("教练不存在或未通过审核");
        }
        
        // 检查学员已匹配教练数量是否已达上限
        int currentMatches = matchMapper.countAcceptedMatchesByStudent(studentUUID);
        if (currentMatches >= student.getMaxCoaches()) {
            throw new RuntimeException("学员已匹配教练数量已达上限");
        }
        
        // 检查是否已经发送过申请
        CoachStudentMatch existingRequest = matchMapper.selectByStudentAndCoach(studentUUID, coachUUID);
        if (existingRequest != null) {
            throw new RuntimeException("已经向该教练发送过申请");
        }
        
        // 创建匹配申请
        CoachStudentMatch matchRequest = CoachStudentMatch.builder()
                .id(UUID.randomUUID())
                .studentId(studentUUID)
                .coachId(coachUUID)
                .status("pending")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        matchMapper.insertMatchRequest(matchRequest);
        
        return MatchRequestVO.builder()
                .id(matchRequest.getId().toString())
                .studentId(matchRequest.getStudentId().toString())
                .coachId(matchRequest.getCoachId().toString())
                .status(matchRequest.getStatus())
                .createdTime(matchRequest.getCreateTime())
                .updatedTime(matchRequest.getUpdateTime())
                .build();
    }

    @Override
    public MatchRequestPageVO getMatchRequests(MatchRequestQueryDTO queryDTO, String currentUserId, String userRole) {
        log.info("获取匹配申请列表: currentUserId={}, userRole={}", currentUserId, userRole);
        
        UUID userUUID = UUID.fromString(currentUserId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询申请列表
        List<MatchRequestDetailVO> requests = matchMapper.selectMatchRequests(
            userUUID, userRole, queryDTO, offset, limit);
        
        // 查询总数
        long total = matchMapper.countMatchRequests(userUUID, userRole, queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return MatchRequestPageVO.builder()
                .requests(requests)
                .pagination(pagination)
                .build();
    }

    @Transactional
    @Override
    public MatchRequestVO updateMatchRequest(String requestId, MatchRequestUpdateDTO updateDTO, 
                                           String currentUserId, String userRole) {
        log.info("处理匹配申请: requestId={}, status={}", requestId, updateDTO.getStatus());
        
        UUID requestUUID = UUID.fromString(requestId);
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        // 验证权限：只有教练可以处理指向自己的申请
        if (!"coach".equals(userRole)) {
            throw new RuntimeException("无权处理匹配申请");
        }
        
        // 查询申请信息
        CoachStudentMatch matchRequest = matchMapper.selectById(requestUUID);
        if (matchRequest == null) {
            throw new RuntimeException("申请不存在");
        }
        
        // 验证申请是否属于当前教练
        if (!matchRequest.getCoachId().equals(currentUserUUID)) {
            throw new RuntimeException("无权处理此申请");
        }
        
        // 检查申请是否已被处理
        if (!"pending".equals(matchRequest.getStatus())) {
            throw new RuntimeException("申请已被处理过");
        }
        
        // 如果接受申请，检查教练是否已达学员上限
        if ("accepted".equals(updateDTO.getStatus())) {
            Coach coach = coachMapper.selectByUserId(currentUserUUID);
            int currentStudents = matchMapper.countAcceptedMatchesByCoach(currentUserUUID);
            
            if (currentStudents >= coach.getMaxStudents()) {
                throw new RuntimeException("教练已接收学员数量已达上限");
            }
        }
        
        // 更新申请状态
        matchMapper.updateMatchStatus(requestUUID, updateDTO.getStatus());
        
        // 返回更新后的申请信息
        MatchRequestQueryDTO queryDTO = new MatchRequestQueryDTO();
        List<MatchRequestDetailVO> requests = matchMapper.selectMatchRequests(
            currentUserUUID, userRole, queryDTO, 0, 1);
        
        return requests.stream()
                .filter(r -> r.getId().equals(requestId))
                .findFirst()
                .map(r -> MatchRequestVO.builder()
                        .id(r.getId())
                        .studentId(r.getStudentId())
                        .coachId(r.getCoachId())
                        .status(r.getStatus())
                        .createdTime(r.getCreatedTime())
                        .updatedTime(r.getUpdatedTime())
                        .build())
                .orElseThrow(() -> new RuntimeException("申请信息获取失败"));
    }
}