package com.system.interceptor;

import com.system.context.UserContext;
import com.system.exception.UnauthorizedException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 清理之前的上下文（确保线程安全）
        UserContext.clear();

        // 调试：打印所有请求头
        System.out.println("=== 拦截器调试信息 ===");
        System.out.println("请求URL: " + request.getRequestURL());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("请求头: " + headerName + " = " + request.getHeader(headerName));
        }

        // 从请求头获取用户ID（尝试多种可能的header名称）
        String userId = request.getHeader("X-User-Id");
        if (userId == null) {
            userId = request.getHeader("x-user-id");
        }
        if (userId == null) {
            userId = request.getHeader("X-USER-ID");
        }
        if (userId == null) {
            userId = request.getHeader("userId");
        }

        System.out.println("最终获取的用户ID: " + userId);

        if (userId != null && !userId.trim().isEmpty()) {
            try {
                UserContext.setUserId(UUID.fromString(userId.trim()));
                System.out.println("成功设置用户ID到上下文: " + UserContext.getUserId());
            } catch (IllegalArgumentException e) {
                System.out.println("用户ID格式错误: " + userId);
                throw new UnauthorizedException("无效的用户ID格式: " + userId);
            }
        } else {
            System.out.println("警告：未找到用户ID请求头");
            // 根据业务需求决定是否抛出异常
            // throw new UnauthorizedException("缺少用户ID");
        }

        System.out.println("拦截器执行完成");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) {
        // 清理线程局部变量，防止内存泄漏
        UserContext.clear();
        System.out.println("清理用户上下文");
    }
}