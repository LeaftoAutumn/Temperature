package com.system.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true); // 允许凭证
        config.addAllowedOrigin("http://localhost:5174"); // 允许的源
        config.addAllowedHeader("*"); // 允许任何请求头
        config.addAllowedMethod("*"); // 允许任何方法（GET, POST等）
        
        source.registerCorsConfiguration("/**", config); // 应用于所有路径
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0); // 设置过滤器优先级
        return bean;
    }
}