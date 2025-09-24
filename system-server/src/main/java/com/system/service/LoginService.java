package com.system.service;

import com.system.dto.LoginDTO;
import com.system.entity.User;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return User 用户信息
     */
    public User login(LoginDTO loginDTO);
}
