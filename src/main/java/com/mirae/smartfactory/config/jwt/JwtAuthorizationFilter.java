package com.mirae.smartfactory.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mirae.smartfactory.consts.ConditionCode.*;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        try {
            log.debug("token: {}", token);
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                System.out.println(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", WRONG_TOKEN.getCode());
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", token);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", UNKNOWN_ERROR.getCode());
        }

        filterChain.doFilter(request, response);


    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return token.substring(7);
        }
        return null;
    }
}
