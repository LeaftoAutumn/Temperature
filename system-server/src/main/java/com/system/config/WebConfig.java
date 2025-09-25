package com.system.config;

import com.system.interceptor.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    
    @Autowired
    private UserContextInterceptor userContextInterceptor;
    
    @PostConstruct
    public void init() {
        logger.info("WebConfig初始化完成");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("注册拦截器UserContextInterceptor");
        registry.addInterceptor(userContextInterceptor)
                .addPathPatterns("localhost:8080/**")
                .excludePathPatterns("/error");
    }
}