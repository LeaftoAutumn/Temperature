package com.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "system.jwt")
public class JwtConfig {
    private String secretKey;
    private long ttl; // token有效期(毫秒)
    private String tokenHeader; // 通常为 "Authorization"
    private String tokenPrefix; // 通常为 "Bearer "
}