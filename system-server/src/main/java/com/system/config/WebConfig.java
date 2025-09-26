package com.system.config;

import com.system.interceptor.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication
@Order(1) // 确保优先加载
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    // 方法1：使用@Bean方式创建拦截器
    public UserContextInterceptor userContextInterceptor() {
        logger.info("✅ 创建 UserContextInterceptor Bean");
        return new UserContextInterceptor();
    }

    // 方法2：直接注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("🎯 === 开始注册拦截器 ===");

        // 创建拦截器实例
        UserContextInterceptor interceptor = userContextInterceptor();
        logger.info("🎯 拦截器实例: {}", interceptor);
        logger.info("🎯 拦截器类: {}", interceptor.getClass().getName());

        // 注册拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .order(0); // 设置最高优先级

        logger.info("✅ 拦截器注册完成，路径: /**");

        // 验证注册
        logger.info("✅ 验证: 拦截器已添加到Spring MVC拦截器链");
    }
}