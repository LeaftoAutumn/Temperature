package com.system.controller;

import com.system.context.UserContext;
import com.system.dto.EvaluationCreateDTO;
import com.system.dto.EvaluationQueryDTO;
import com.system.service.EvaluationService;
import com.system.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evaluations")
@Slf4j
@Api(tags = "评价管理")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/pending")
    @ApiOperation("获取待评价课程列表")
    public List<PendingEvaluationVO> getPendingEvaluations(HttpServletRequest request) {
        log.info("获取待评价课程列表: currentUserId={}", UserContext.getUserId());
        return evaluationService.getPendingEvaluations(UserContext.getUserId());
    }

    @PostMapping
    @ApiOperation("提交课程评价")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluationResponseVO submitEvaluation(@Valid @RequestBody EvaluationCreateDTO createDTO,
                                               HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("提交课程评价: currentUserId={}", currentUserId);
        return evaluationService.submitEvaluation(createDTO, currentUserId);
    }

    @GetMapping("/received")
    @ApiOperation("获取收到的评价列表")
    public EvaluationPageVO getReceivedEvaluations(@Valid EvaluationQueryDTO queryDTO,
                                                 HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取收到的评价列表: currentUserId={}", currentUserId);
        return evaluationService.getReceivedEvaluations(queryDTO, currentUserId);
    }

    @GetMapping("/given")
    @ApiOperation("获取给出的评价列表")
    public EvaluationPageVO getGivenEvaluations(@Valid EvaluationQueryDTO queryDTO,
                                              HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取给出的评价列表: currentUserId={}", currentUserId);
        return evaluationService.getGivenEvaluations(queryDTO, currentUserId);
    }

    @GetMapping("/stats")
    @ApiOperation("获取评价统计信息")
    public EvaluationStatsVO getEvaluationStats(@Valid String coachId, HttpServletRequest request) {
        log.info("获取评价统计信息: currentUserId={}", coachId);
        return evaluationService.getEvaluationStats(coachId);
    }

    @GetMapping("/courses/{courseId}/evaluations")
    @ApiOperation("获取课程评价详情")
    public CourseEvaluationVO getCourseEvaluations(@PathVariable String courseId,
                                                 HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取课程评价详情: courseId={}, currentUserId={}", courseId, currentUserId);
        return evaluationService.getCourseEvaluations(courseId, currentUserId);
    }
}