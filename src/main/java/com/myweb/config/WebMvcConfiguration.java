package com.myweb.config;

import com.myweb.interceptor.LoginInterceptor;
import com.myweb.interceptor.ReLoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH= Arrays.asList("/","/css/**","/js/**","/img/**","/media/**","/vendors/**","/layui/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/");
//        registry.addInterceptor(ReLoginCheckInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/**")
//                .excludePathPatterns(EXCLUDE_PATH);
    }

    @Bean
    public LoginInterceptor LoginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public ReLoginCheckInterceptor ReLoginCheckInterceptor(){
        return new ReLoginCheckInterceptor();
    }



}

















