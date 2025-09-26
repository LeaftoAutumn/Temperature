package com.system.interceptor;

import com.system.context.UserContext;
import com.system.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    // 静态初始化块，确保类被加载
    static {
        System.out.println("✅ UserContextInterceptor 类被加载");
    }

    public UserContextInterceptor() {
        System.out.println("✅ UserContextInterceptor 实例被创建");
        System.out.println("✅ 实例哈希码: " + this.hashCode());
        System.out.println("✅ 线程: " + Thread.currentThread().getName());
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("\n🎯 ===== 拦截器 PRE-HANDLE 开始 =====");
        System.out.println("🎯 线程: " + Thread.currentThread().getName());
        System.out.println("🎯 拦截器实例: " + this.hashCode());
        System.out.println("🎯 请求URL: " + request.getRequestURL());
        System.out.println("🎯 请求URI: " + request.getRequestURI());
        System.out.println("🎯 请求方法: " + request.getMethod());
        System.out.println("🎯 远程地址: " + request.getRemoteAddr());

        // 打印所有请求头
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.toLowerCase().contains("user") ||
                    headerName.toLowerCase().contains("id") ||
                    headerName.toLowerCase().contains("auth")) {
                System.out.println("🎯 请求头[" + headerName + "]: " + request.getHeader(headerName));
            }
        }

        // 清理上下文
        UserContext.clear();

        // 获取用户ID
        String userId = getUserIdFromHeaders(request);
        System.out.println("🎯 提取的用户ID: " + userId);

        if (userId != null && !userId.trim().isEmpty()) {
            UserContext.setUserId(userId.trim());
            System.out.println("✅ 用户ID设置成功: " + UserContext.getUserId());
        } else {
            System.out.println("⚠️ 未找到用户ID");
        }

        System.out.println("✅ 拦截器 PRE-HANDLE 完成");
        System.out.println("🎯 ===== 拦截器 PRE-HANDLE 结束 =====\n");
        return true;
    }

    private String getUserIdFromHeaders(HttpServletRequest request) {
        String[] headers = {"x-user-id", "X-User-Id", "X-USER-ID", "userId", "User-Id", "user-id", "authorization"};

        for (String header : headers) {
            String value = request.getHeader(header);
            if (value != null && !value.trim().isEmpty()) {
                System.out.println("🎯 从Header[" + header + "]获取值: " + value);

                // 如果是authorization头，尝试提取用户ID
                if (header.equalsIgnoreCase("authorization") && value.startsWith("Bearer ")) {
                    // 这里可以解析JWT token获取用户ID
                    System.out.println("🎯 找到Authorization头，但需要解析JWT");
                } else {
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) {
        System.out.println("🎯 拦截器 AFTER-COMPLETION 清理上下文");
        UserContext.clear();
    }
}