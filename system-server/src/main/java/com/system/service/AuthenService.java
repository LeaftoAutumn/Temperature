package com.system.service;

import com.system.dto.LoginDTO;
import com.system.dto.RegisterDTO;
import com.system.entity.User;

public interface AuthenService {

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return User 用户信息
     */
    public User login(LoginDTO loginDTO);

    /**
     * 用户注册

     * @param registerDTO 注册信息
     */
    void register(RegisterDTO registerDTO);
}
