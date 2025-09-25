package com.system.interceptor;// UserContextInterceptor.java
import com.system.context.UserContext;
import com.system.exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class UserContextInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) throws Exception {
        // 从请求头、Session或Token中获取用户ID
        String userIdStr = request.getHeader("X-User-Id");
        if (userIdStr != null) {
            try {
                UserContext.setUserId(UUID.fromString(userIdStr));
            } catch (NumberFormatException e) {
                throw new UnauthorizedException("无效的用户ID格式");
            }
        }
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, 
                              HttpServletResponse response, 
                              Object handler, Exception ex) {
        // 清理线程局部变量，防止内存泄漏
        UserContext.clear();
    }
}