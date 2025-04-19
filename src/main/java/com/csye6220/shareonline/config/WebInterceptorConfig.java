package com.csye6220.shareonline.config;

import com.csye6220.shareonline.config.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/posts/**", "/comments/**", "/bookmarks/**", "/feed")
                .excludePathPatterns(
                        "/api/auth/**",
                        "/login",
                        "/register",
                        "/logout",        // ← 新增
                        "/css/**",
                        "/js/**",
                        "/images/**"
                );
    }
}
