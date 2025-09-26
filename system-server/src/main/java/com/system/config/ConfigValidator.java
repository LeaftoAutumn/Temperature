package com.system.config;

import com.system.interceptor.UserContextInterceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class ConfigValidator {
    
    @Bean
    public CommandLineRunner validateConfig(ApplicationContext context) {
        return args -> {
            System.out.println("\n🔍 === 配置验证开始 ===");
            
            // 检查拦截器Bean是否存在
            try {
                HandlerInterceptor interceptor = context.getBean(UserContextInterceptor.class);
                System.out.println("✅ UserContextInterceptor Bean 存在: " + interceptor);
            } catch (Exception e) {
                System.out.println("❌ UserContextInterceptor Bean 未找到");
            }
            
            // 检查WebConfig Bean
            try {
                WebConfig webConfig = context.getBean(WebConfig.class);
                System.out.println("✅ WebConfig Bean 存在: " + webConfig);
            } catch (Exception e) {
                System.out.println("❌ WebConfig Bean 未找到");
            }
            
            System.out.println("🔍 === 配置验证完成 ===\n");
        };
    }
}