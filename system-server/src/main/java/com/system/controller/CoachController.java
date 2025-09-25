// CoachController.java
package com.system.controller;

import com.system.dto.CoachQueryDTO;
import com.system.service.CoachService;
import com.system.vo.CoachDetailVO;
import com.system.vo.CoachListItemVO;
import com.system.vo.CoachPageVO;
import com.system.vo.StudentListItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@Api(tags = "用户信息查询与教练学员匹配管理")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("/coaches")
    @ApiOperation("查询教练列表")
    public CoachPageVO listCoaches(@Valid CoachQueryDTO queryDTO) {
        log.info("查询教练列表: campusId={}", queryDTO.getCampusId());
        return coachService.getCoaches(queryDTO);
    }

    @GetMapping("/coaches/{coachId}")
    @ApiOperation("获取教练详细信息")
    public CoachDetailVO getCoachDetail(@PathVariable UUID coachId) {
        log.info("获取教练详细信息: coachId={}", coachId);
        return coachService.getCoachDetail(coachId);
    }

    @GetMapping("/students/{studentId}/coaches")
    @ApiOperation("获取学员的已匹配教练列表")
    public List<CoachListItemVO> listStudentCoaches(@PathVariable String studentId,
                                                  HttpServletRequest request) {
        log.info("获取学员的已匹配教练列表: studentId={}", studentId);
        return coachService.getStudentCoaches(studentId);
    }

    @GetMapping("/coaches/{coachId}/students")
    @ApiOperation("获取教练的已接收学员列表")
    public List<StudentListItemVO> listCoachStudents(@PathVariable UUID coachId,
                                                   HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取教练的已接收学员列表: coachId={}, currentUserId={}", coachId, currentUserId);
        return coachService.getCoachStudents(coachId, currentUserId);
    }
}