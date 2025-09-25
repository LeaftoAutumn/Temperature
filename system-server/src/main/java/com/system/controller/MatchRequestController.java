// MatchRequestController.java
package com.system.controller;

import com.system.dto.MatchRequestCreateDTO;
import com.system.dto.MatchRequestQueryDTO;
import com.system.dto.MatchRequestUpdateDTO;
import com.system.service.MatchRequestService;
import com.system.vo.MatchRequestPageVO;
import com.system.vo.MatchRequestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/coach-match-requests")
@Slf4j
@Api(tags = "用户信息查询与教练学员匹配管理")
public class MatchRequestController {

    @Autowired
    private MatchRequestService matchRequestService;

    @PostMapping
    @ApiOperation("发送双选申请")
    @ResponseStatus(HttpStatus.CREATED)
    public MatchRequestVO createMatchRequest(@Valid @RequestBody MatchRequestCreateDTO createDTO,
                                           HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("发送双选申请: currentUserId={}", currentUserId);
        return matchRequestService.createMatchRequest(createDTO, currentUserId);
    }

    @GetMapping
    @ApiOperation("获取匹配申请列表")
    public MatchRequestPageVO listMatchRequests(@Valid MatchRequestQueryDTO queryDTO,
                                              HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("获取匹配申请列表: currentUserId={}, userRole={}", currentUserId, userRole);
        return matchRequestService.getMatchRequests(queryDTO, currentUserId, userRole);
    }

    @PutMapping("/{requestId}")
    @ApiOperation("处理匹配申请")
    public MatchRequestVO updateMatchRequest(@PathVariable String requestId,
                                           @Valid @RequestBody MatchRequestUpdateDTO updateDTO,
                                           HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("处理匹配申请: requestId={}, currentUserId={}, userRole={}", 
                requestId, currentUserId, userRole);
        return matchRequestService.updateMatchRequest(requestId, updateDTO, currentUserId, userRole);
    }
}