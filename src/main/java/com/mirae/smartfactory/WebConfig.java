package com.mirae.smartfactory;

import com.mirae.smartfactory.interceptor.AdminLoginCheckInterceptor;
import com.mirae.smartfactory.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AdminLoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/statistics/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/admin/**", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*") // 기타 설정
                .allowedHeaders("*");;
    }
}
