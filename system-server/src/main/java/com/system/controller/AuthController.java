// AuthController.java
package com.system.controller;

import com.system.dto.LoginRequestDTO;
import com.system.dto.RegisterRequestDTO;
import com.system.service.AuthService;
import com.system.vo.LoginResponseVO;
import com.system.vo.UserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Slf4j
@Api(tags = "认证与授权")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public LoginResponseVO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("用户登录请求: {}", loginRequestDTO.getUsername());
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailVO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        log.info("用户注册请求: {}", registerRequestDTO.getUsername());
        return authService.register(registerRequestDTO);
    }

    @GetMapping("/me")
    @ApiOperation("获取当前用户信息")
    public UserDetailVO getCurrentUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        log.info("获取当前用户信息: {}", userId);
        return authService.getCurrentUser(userId);
    }
}