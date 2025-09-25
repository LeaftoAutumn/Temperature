package com.system.interceptor;

import com.system.config.JwtConfig;
import com.system.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行OPTIONS请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 从请求头获取token
        String token = request.getHeader(jwtConfig.getTokenHeader());
        if (token == null || !token.startsWith(jwtConfig.getTokenPrefix())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未提供有效的Token");
            return false;
        }

        // 提取并验证token
        token = token.substring(jwtConfig.getTokenPrefix().length());
        if (!jwtService.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token无效或已过期");
            return false;
        }

        // 将用户信息存入request
        Claims claims = jwtService.parseToken(token);
        request.setAttribute("userId", claims.get("userId"));
        request.setAttribute("role", claims.get("role"));

        return true;
    }
}