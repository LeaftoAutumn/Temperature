package com.system.service.impl;

import com.system.constant.MessageConstant;
import com.system.dto.LoginDTO;
import com.system.dto.RegisterDTO;
import com.system.entity.User;
import com.system.exception.AccountNotFoundException;
import com.system.exception.PasswordErrorException;
import com.system.mapper.UserMapper;
import com.system.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = User.builder()
                .userId(UUID.randomUUID())
                .username(registerDTO.getUsername())
                .password(DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes()))
                .name(registerDTO.getName())
                .gender(User.Gender.valueOf(registerDTO.getGender().toUpperCase()))
                .birthday(registerDTO.getBirthday())
                .phoneNumber(registerDTO.getPhoneNumber())
                .email(registerDTO.getEmail())
                .campusId(UUID.fromString(registerDTO.getCampusId()))
                .role(User.Role.valueOf(registerDTO.getRole().toUpperCase()))
                .createTime(String.valueOf(LocalDateTime.now()))
                .build();

        userMapper.createUser(Collections.singletonList(user));
    }

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return User 用户信息
     */
    @Override
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对
        // 进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return user;
    }
}
