package com.system.aspect;// RoleCheckAspect.java
import com.system.annotation.RequireRole;
import com.system.context.UserContext;
import com.system.entity.User;
import com.system.enumeration.UserRole;
import com.system.exception.UnauthorizedException;
import com.system.mapper.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Aspect
@Component
public class RoleCheckAspect {
    
    @Autowired
    private UserMapper userMapper;
    
    @Around("@annotation(com.system.annotation.RequireRole)")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名和注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequireRole requireRole = method.getAnnotation(RequireRole.class);
        
        // 获取当前用户ID
        UUID userId = UserContext.getUserId();
        if (userId == null) {
            throw new UnauthorizedException("用户未登录或会话已过期");
        }
        
        // 查询用户角色
        User user = userMapper.selectById(userId);
        UserRole userRole = UserRole.valueOf(user.getRole());

        if (userRole == null) {
            throw new UnauthorizedException("用户角色不存在");
        }
        
        // 校验权限
        checkPermission(requireRole, userRole);
        
        // 设置用户角色到上下文（可选）
        UserContext.setUserRole(userRole);
        
        try {
            // 执行原方法
            return joinPoint.proceed();
        } finally {
            // 清理角色信息（可选）
            UserContext.setUserRole(null);
        }
    }
    
    private void checkPermission(RequireRole requireRole, UserRole userRole) {
        UserRole[] requiredRoles = requireRole.value();
        RequireRole.Logic logic = requireRole.logic();
        
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
            // AND 逻辑：需要拥有所有指定角色（这里简化处理，通常用户只有一个角色）
            if (!requiredRoleList.contains(userRole)) {
                throw new UnauthorizedException(
                    String.format("需要角色: %s，当前角色: %s", 
                    Arrays.toString(requiredRoles), userRole)
                );
            }
        }
    }
}