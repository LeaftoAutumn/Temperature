// SystemController.java
package com.system.controller;

import com.system.dto.SystemActivateDTO;
import com.system.service.SystemService;
import com.system.vo.SystemStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/system")
@Slf4j
@Api(tags = "系统管理功能")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @PostMapping("/activate")
    @ApiOperation("系统激活")
    public void activateSystem(@Valid @RequestBody SystemActivateDTO activateDTO,
                             HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("系统激活: currentUserId={}, userRole={}", currentUserId, userRole);
        
        // 权限验证 - 只有超级管理员可以激活系统
        if (!"super_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        systemService.activateSystem(activateDTO, currentUserId);
    }

    @GetMapping("/status")
    @ApiOperation("获取系统状态")
    public SystemStatusVO getSystemStatus(HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        log.info("获取系统状态: currentUserId={}, userRole={}", currentUserId, userRole);
        
        // 权限验证 - 只有管理员可以查看系统状态
        if (!"super_admin".equals(userRole) && !"campus_admin".equals(userRole)) {
            throw new RuntimeException("权限不足");
        }
        
        return systemService.getSystemStatus();
    }
}