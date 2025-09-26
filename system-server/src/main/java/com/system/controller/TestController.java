package com.system.controller;

import com.system.annotation.RequireRole;
import com.system.context.UserContext;
import com.system.enumeration.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/role-check")
    @RequireRole({UserRole.COACH, UserRole.STUDENT})
    public String testRoleCheck() {
        String userId = String.valueOf(UserContext.getUserId());
        return "角色检查通过！当前用户ID: " + userId;
    }

    @GetMapping("/test/user-context")
    public String testUserContext() {
        String userId = String.valueOf(UserContext.getUserId());
        return "直接获取用户ID: " + (userId != null ? userId : "null");
    }
}