package com.mirae.smartfactory.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtTokenProvider jwtTokenProvider;

    private final RedisTemplate redisTemplate;



    /**
     * JWT를 검증하는 필터
     * HttpServletRequest 의 Authorization 헤더에서 JWT token을 찾고 그것이 맞는지 확인
     * UsernamePasswordAuthenticationFilter 앞에서 작동
     * (JwtTokenFilterConfigurer 참고)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("####doFilterInternal진입");
        String jwt = resolveToken(request, AUTHORIZATION_HEADER);
        System.out.println("$$$$$$$$$$$$$$$$$$$jwt:" + jwt);

        try{
            if ( jwt != null && jwtTokenProvider.validateToken(jwt)) {
                System.out.println("validate 성공");
                String isLogout;
                try{
                    isLogout = (String) redisTemplate.opsForValue().get(jwt);
                } catch (Exception e) {
                    isLogout = "";
                }

                System.out.println("로그아웃 체크");

                if(ObjectUtils.isEmpty(isLogout)) {
                    System.out.println("isEmpty logout");
                    Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                    System.out.println("authentication + " + authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("set Authentication to security context for '{}', uri: {}", authentication.getName(), request.getRequestURI());
                }
            }
        } catch (SignatureException e) {
            System.out.println("error 1");
            request.setAttribute("exception", e);
        } catch (MalformedJwtException e) {
            System.out.println("error 2");
            request.setAttribute("exception", e);
        } catch (ExpiredJwtException e) {
            System.out.println("error 3");
            request.setAttribute("exception", e);
        } catch (UnsupportedJwtException e) {
            System.out.println("error 4");
            request.setAttribute("exception", e);
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("error 5");
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        System.out.println("Bearer Token:" + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
