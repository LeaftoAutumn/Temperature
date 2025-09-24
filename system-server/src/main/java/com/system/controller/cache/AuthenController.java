package com.system.controller.cache;

import com.system.constant.JwtClaimsConstant;
import com.system.context.BaseContext;
import com.system.dto.LoginDTO;
import com.system.dto.RegisterDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/coach/auth")
@Slf4j
@Api("教练认证")
public class AuthenController {

    @Autowired
    private AuthenService authenService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 教练登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("教练登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("教练登录：{}", loginDTO);

        User user = authenService.login(loginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.COACH_ID, user.getUserId());
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
