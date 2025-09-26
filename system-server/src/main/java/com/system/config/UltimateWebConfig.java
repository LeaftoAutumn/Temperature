package com.system.config;

import com.system.interceptor.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class UltimateWebConfig implements WebMvcConfigurer {
    
    @Autowired(required = false)
    private UserContextInterceptor userContextInterceptor;
    
    @PostConstruct
    public void init() {
        System.out.println("🔥 UltimateWebConfig 初始化");
        System.out.println("🔥 拦截器自动注入: " + userContextInterceptor);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("🔥 === 终极拦截器注册开始 ===");
        
        if (userContextInterceptor != null) {
            System.out.println("🔥 使用自动注入的拦截器");
            InterceptorRegistration registration = registry.addInterceptor(userContextInterceptor);
            registration.addPathPatterns("/**");
            registration.excludePathPatterns("/error");
            registration.order(0);
            System.out.println("🔥 拦截器注册成功");
        } else {
            System.out.println("🔥 拦截器自动注入失败，尝试手动创建");
            try {
                UserContextInterceptor interceptor = new UserContextInterceptor();
                InterceptorRegistration registration = registry.addInterceptor(interceptor);
                registration.addPathPatterns("/**");
                registration.excludePathPatterns("/error");
                registration.order(0);
                System.out.println("🔥 手动创建拦截器成功");
            } catch (Exception e) {
                System.out.println("🔥 手动创建拦截器失败: " + e.getMessage());
            }
        }
        
        System.out.println("🔥 === 终极拦截器注册完成 ===");
    }
}