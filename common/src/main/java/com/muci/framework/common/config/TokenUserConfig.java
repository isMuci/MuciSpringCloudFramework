package com.muci.framework.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.muci.framework.common.interceptor.TokenUserInterceptor;

@Configuration
public class TokenUserConfig implements WebMvcConfigurer {

    @Autowired
    TokenUserInterceptor tokenUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenUserInterceptor).addPathPatterns("/**");
    }
}