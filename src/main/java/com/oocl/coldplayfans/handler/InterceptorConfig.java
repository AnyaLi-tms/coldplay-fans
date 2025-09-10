package com.oocl.coldplayfans.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new JwtInterceptor())
                .excludePathPatterns(
                        "/health",
                        "/user/login",
                        "/user/register",
                        "/banners",
                        "/banners/*",
                        "/concerts",
                        "/concerts/*",
                        "/merchandises");
    }
}
