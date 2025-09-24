package com.system.controller.admin;

import com.system.constant.JwtClaimsConstant;
import com.system.context.BaseContext;
import com.system.dto.*;
import com.system.entity.User;
import com.system.properties.JwtProperties;
import com.system.result.Result;
import com.system.service.LoginService;
import com.system.utils.JwtUtil;
import com.system.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/admin")
@Slf4j
@Api(tags = "管理员管理")
public class AdminController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("管理员登录：{}", loginDTO);

        User user = loginService.login(loginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        LoginVO loginVO = LoginVO.builder()
                .token(token)
                .user(user)
                .build();

        return Result.success(loginVO);
    }

    /**
     * 用户出登录
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public Result<String> logout() {
        log.info("用户{}退出登录", BaseContext.getCurrentId());

        //清除ThreadLocal中的数据
        BaseContext.removeCurrentId();

        return Result.success();
    }
}
