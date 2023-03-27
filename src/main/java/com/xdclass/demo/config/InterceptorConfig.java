package com.xdclass.demo.config;

import com.xdclass.demo.interceptor.CorsInterceptor;
import com.xdclass.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
