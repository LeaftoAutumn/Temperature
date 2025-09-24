package com.system.controller.admin;

import com.system.constant.JwtClaimsConstant;
import com.system.context.BaseContext;
import com.system.dto.LoginDTO;
import com.system.entity.User;
import com.system.properties.JwtProperties;
import com.system.result.Result;
import com.system.service.AuthenService;
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
 * 管理员认证
 */
@RestController
@RequestMapping("/admin/auth")
@Slf4j
@Api(tags = "管理员认证")
public class AuthenController {

    @Autowired
    private AuthenService authenService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 管理员登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("管理员登录：{}", loginDTO);

        User user = authenService.login(loginDTO);

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
}
