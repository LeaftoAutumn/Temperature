package com.system.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 重要修改：使用addAllowedOriginPattern而不是addAllowedOrigin
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:*"); // 允许所有localhost端口
        config.addAllowedOriginPattern("http://127.0.0.1:*"); // 也允许IP形式的localhost

        // 或者更宽松的配置（开发环境）
        // config.addAllowedOriginPattern("*"); // 但注意：与setAllowCredentials(true)冲突

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // 重要：设置预检请求的缓存时间
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}