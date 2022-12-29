package com.mirae.smartfactory.config.security;

import com.mirae.smartfactory.config.security.jwt.JwtAccessDeniedHandler;
import com.mirae.smartfactory.config.security.jwt.JwtAuthenticationEntryPoint;
import com.mirae.smartfactory.config.security.jwt.JwtTokenFilterConfigurer;
import com.mirae.smartfactory.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CorsFilter corsFilter;


    @Bean // 인증 실패 처리 관련 객체
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean // 비밀번호 암호화 객체
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/members/login", "/members/reissue");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패시 오류 처리
                .accessDeniedHandler(jwtAccessDeniedHandler) // 권한 부족시 오류 처리

                .and()
                .addFilter(corsFilter) //@CrossOrigin(인증x), 시큐리티 필터에 등록 인증(o)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/members/**")
                .access("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/mirae/**")
                .access("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/resource/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/statistics/**")
                .access("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()

                .and()
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider)); // Jwt 관련 필터 추가
    }

}