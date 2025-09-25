package com.system.service.impl;

import com.system.dto.EvaluationCreateDTO;
import com.system.dto.EvaluationQueryDTO;
import com.system.entity.Course;
import com.system.entity.Evaluation;
import com.system.mapper.CourseMapper;
import com.system.mapper.EvaluationMapper;
import com.system.mapper.UserMapper;
import com.system.service.EvaluationService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<PendingEvaluationVO> getPendingEvaluations(String userId) {
        log.info("获取待评价课程列表: userId={}", userId);
        
        return evaluationMapper.selectPendingEvaluations(UUID.fromString(userId));
    }

    @Transactional
    @Override
    public EvaluationResponseVO submitEvaluation(EvaluationCreateDTO createDTO, String userId) {
        log.info("提交课程评价: userId={}, courseId={}", userId, createDTO.getCourseId());
        
        UUID fromUserId = UUID.fromString(userId);
        UUID courseId = UUID.fromString(createDTO.getCourseId());
        UUID toUserId = UUID.fromString(createDTO.getToUserId());
        
        // 验证课程是否存在且已完成
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        
        if (!"completed".equals(course.getStatus())) {
            throw new RuntimeException("课程未完成，不能评价");
        }
        
        // 验证评价人是否参与该课程
        if (!course.getStudentId().equals(fromUserId) && !course.getCoachId().equals(fromUserId)) {
            throw new RuntimeException("您未参与该课程，不能评价");
        }
        
        // 验证被评价人是否参与该课程
        if (!course.getStudentId().equals(toUserId) && !course.getCoachId().equals(toUserId)) {
            throw new RuntimeException("被评价人未参与该课程");
        }
        
        // 验证是否重复评价
        if (evaluationMapper.existsByCourseAndUsers(courseId, fromUserId, toUserId)) {
            throw new RuntimeException("您已经评价过该课程");
        }
        
        // 创建评价记录
        Evaluation evaluation = Evaluation.builder()
                .id(UUID.randomUUID())
                .courseId(courseId)
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .content(createDTO.getContent())
                .rating(createDTO.getRating())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        evaluationMapper.insertEvaluation(evaluation);
        
        return EvaluationResponseVO.builder()
                .id(evaluation.getId().toString())
                .message("评价提交成功")
                .build();
    }

    @Override
    public EvaluationPageVO getReceivedEvaluations(EvaluationQueryDTO queryDTO, String userId) {
        log.info("获取收到的评价列表: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询评价列表
        List<EvaluationDetailVO> evaluations = evaluationMapper.selectReceivedEvaluations(
            userUUID, queryDTO, offset, limit);
        
        // 查询总数
        long total = evaluationMapper.countReceivedEvaluations(userUUID, queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return EvaluationPageVO.builder()
                .data(evaluations)
                .pagination(pagination)
                .build();
    }

    @Override
    public EvaluationPageVO getGivenEvaluations(EvaluationQueryDTO queryDTO, String userId) {
        log.info("获取给出的评价列表: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询评价列表
        List<EvaluationDetailVO> evaluations = evaluationMapper.selectGivenEvaluations(
            userUUID, queryDTO, offset, limit);
        
        // 查询总数
        long total = evaluationMapper.countGivenEvaluations(userUUID, queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return EvaluationPageVO.builder()
                .data(evaluations)
                .pagination(pagination)
                .build();
    }

    @Override
    public CourseEvaluationVO getCourseEvaluations(String courseId, String userId) {
        log.info("获取课程评价详情: courseId={}, userId={}", courseId, userId);
        
        UUID courseUUID = UUID.fromString(courseId);
        UUID userUUID = UUID.fromString(userId);
        
        // 验证课程是否存在且用户有权访问
        Course course = courseMapper.selectById(courseUUID);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        
        if (!course.getStudentId().equals(userUUID) && !course.getCoachId().equals(userUUID)) {
            throw new RuntimeException("无权查看该课程的评价");
        }
        
        // 获取课程基本信息
        CourseBasicInfoVO courseInfo = CourseBasicInfoVO.builder()
                .id(course.getId().toString())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .tableNumber(course.getTableNumber())
                .status(course.getStatus())
                .build();
        
        // 获取课程评价
        List<EvaluationDetailVO> evaluations = evaluationMapper.selectByCourseId(courseUUID);
        
        return CourseEvaluationVO.builder()
                .courseInfo(courseInfo)
                .evaluations(evaluations)
                .build();
    }

    @Override
    public EvaluationStatsVO getEvaluationStats(String userId) {
        log.info("获取评价统计信息: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        
        // 获取基本统计信息
        EvaluationStatsVO stats = evaluationMapper.selectEvaluationStats(userUUID);
        if (stats == null) {
            stats = EvaluationStatsVO.builder()
                    .totalReceived(0)
                    .averageRating(0.0)
                    .lastEvaluationTime(null)
                    .build();
        }
        
        // 获取评分分布
        List<Evaluation> evaluations = evaluationMapper.selectByToUserId(userUUID);
        Map<Integer, Integer> ratingDistribution = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            ratingDistribution.put(i, 0);
        }
        
        for (Evaluation eval : evaluations) {
            ratingDistribution.put(eval.getRating(), 
                ratingDistribution.get(eval.getRating()) + 1);
        }
        
        stats.setRatingDistribution(ratingDistribution);
        
        return stats;
    }
}