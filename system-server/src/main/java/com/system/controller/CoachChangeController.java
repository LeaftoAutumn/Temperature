// CoachChangeController.java
package com.system.controller;

import com.system.dto.CoachChangeRequestCreateDTO;
import com.system.service.CoachChangeService;
import com.system.vo.CoachChangeRequestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/coach-change-requests")
@Slf4j
@Api(tags = "用户信息查询与教练学员匹配管理")
public class CoachChangeController {

    @Autowired
    private CoachChangeService coachChangeService;

    @PostMapping
    @ApiOperation("发起更换教练请求")
    @ResponseStatus(HttpStatus.CREATED)
    public CoachChangeRequestVO createCoachChangeRequest(
            @Valid @RequestBody CoachChangeRequestCreateDTO createDTO,
            HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("发起更换教练请求: currentUserId={}", currentUserId);
        return coachChangeService.createCoachChangeRequest(createDTO, currentUserId);
    }
}