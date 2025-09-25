// SystemLogController.java
package com.system.controller;

import com.system.dto.SystemLogQueryDTO;
import com.system.service.SystemLogService;
import com.system.vo.SystemLogPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/system-logs")
@Slf4j
@Api(tags = "系统日志查询")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @GetMapping
    @ApiOperation("查询系统日志")
    public SystemLogPageVO listSystemLogs(@Valid SystemLogQueryDTO queryDTO,
                                        HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("查询系统日志: currentUserId={}, userRole={}", currentUserId, userRole);
        
        // 权限验证 - 只有管理员可以查看系统日志
        if (!"super_admin".equals(userRole) && !"campus_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        return systemLogService.getSystemLogs(queryDTO);
    }
}