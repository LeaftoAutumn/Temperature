package com.system.diagnostic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.Arrays;

@Configuration
public class MvcDiagnostic {
    
    @Bean
    public CommandLineRunner mvcDiagnosticRunner(ApplicationContext context) {
        return args -> {
            System.out.println("\n🔍 === Spring MVC 诊断开始 ===");
            
            // 检查所有拦截器
            String[] interceptorBeans = context.getBeanNamesForType(HandlerInterceptor.class);
            System.out.println("🔍 所有HandlerInterceptor: " + Arrays.toString(interceptorBeans));
            
            // 检查MappedInterceptor
            String[] mappedInterceptorBeans = context.getBeanNamesForType(MappedInterceptor.class);
            System.out.println("🔍 所有MappedInterceptor: " + Arrays.toString(mappedInterceptorBeans));
            
            // 检查WebMvcConfigurer
            String[] configurerBeans = context.getBeanNamesForType(org.springframework.web.servlet.config.annotation.WebMvcConfigurer.class);
            System.out.println("🔍 所有WebMvcConfigurer: " + Arrays.toString(configurerBeans));
            
            System.out.println("🔍 === Spring MVC 诊断完成 ===\n");
        };
    }
}