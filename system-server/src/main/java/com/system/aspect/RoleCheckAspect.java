package com.system.aspect;

import com.system.annotation.RequireRole;
import com.system.context.UserContext;
import com.system.entity.User;
import com.system.enumeration.UserRole;
import com.system.exception.UnauthorizedException;
import com.system.interceptor.UserContextInterceptor;
import com.system.mapper.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Aspect
@Component
@Order(100) // 确保在拦截器之后执行
public class RoleCheckAspect {

    @Autowired
    private UserMapper userMapper;

    @Around("@annotation(com.system.annotation.RequireRole)")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=== Aspect开始执行 ===");

        // 首先检查用户ID是否存在
        System.out.println("检查UserContext是否有用户ID...");
        boolean hasUserId = UserContext.getUserId() != null;
        System.out.println("UserContext.hasUserId()结果: " + hasUserId);

        if (!hasUserId) {
            throw new UnauthorizedException("用户未登录或会话已过期");
        }

        // 获取当前用户ID
        String userIdStr = String.valueOf(UserContext.getUserId());
        UUID userId = UUID.fromString(UserContext.getUserId());

        System.out.println("Aspect中获取的用户ID字符串: " + userIdStr);
        System.out.println("Aspect中获取的用户ID UUID: " + userId);

        if (userIdStr == null || userId == null) {
            throw new UnauthorizedException("用户ID为空，请检查请求头");
        }

        // 获取方法签名和注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequireRole requireRole = method.getAnnotation(RequireRole.class);

        System.out.println("开始查询数据库用户信息...");

        // 查询用户角色
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UnauthorizedException("用户不存在: " + userIdStr);
        }

        System.out.println("数据库查询到的用户: " + user);

        UserRole userRole;
        try {
            userRole = UserRole.valueOf(user.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("无效的用户角色: " + user.getRole());
        }

        System.out.println("用户角色: " + userRole);

        // 校验权限
        checkPermission(requireRole, userRole);

        System.out.println("权限校验通过，执行目标方法");

        try {
            // 执行原方法
            return joinPoint.proceed();
        } finally {
            System.out.println("Aspect执行完成");
        }
    }

    private void checkPermission(RequireRole requireRole, UserRole userRole) {
        UserRole[] requiredRoles = requireRole.value();
        RequireRole.Logic logic = requireRole.logic();

        System.out.println("需要的角色: " + Arrays.toString(requiredRoles));
        System.out.println("逻辑条件: " + logic);

        if (requiredRoles.length == 0) {
            return; // 没有指定角色要求，直接通过
        }

        List<UserRole> requiredRoleList = Arrays.asList(requiredRoles);

        if (logic == RequireRole.Logic.OR) {
            // OR 逻辑：拥有任意一个指定角色即可
            if (!requiredRoleList.contains(userRole)) {
                throw new UnauthorizedException(
                        String.format("需要角色: %s，当前角色: %s",
                                Arrays.toString(requiredRoles), userRole)
                );
            }
        } else {
            // AND 逻辑：需要拥有所有指定角色
            if (!requiredRoleList.contains(userRole)) {
                throw new UnauthorizedException(
                        String.format("需要角色: %s，当前角色: %s",
                                Arrays.toString(requiredRoles), userRole)
                );
            }
        }

        System.out.println("权限校验通过");
    }
}