package com.system.controller.common;

import com.system.context.BaseContext;
import com.system.dto.RegisterDTO;
import com.system.result.Result;
import com.system.service.AuthenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共认证
 */
@RestController
@RequestMapping("/common/auth")
@Slf4j
@Api(tags = "公共认证")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
        log.info("用户注册：{}", registerDTO);

        authenService.register(registerDTO);

        return Result.success();
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public Result<String> logout() {
        log.info("{}退出登录", BaseContext.getCurrentId());

        //清除ThreadLocal中的数据
        BaseContext.removeCurrentId();

        return Result.success();
    }
}
