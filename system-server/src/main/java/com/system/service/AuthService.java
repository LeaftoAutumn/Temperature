// AuthService.java
package com.system.service;

import com.system.dto.LoginRequestDTO;
import com.system.dto.RegisterRequestDTO;
import com.system.vo.LoginResponseVO;
import com.system.vo.UserDetailVO;

public interface AuthService {
    
    LoginResponseVO login(LoginRequestDTO loginRequestDTO);
    
    UserDetailVO register(RegisterRequestDTO registerRequestDTO);
    
    UserDetailVO getCurrentUser(String userId);
}