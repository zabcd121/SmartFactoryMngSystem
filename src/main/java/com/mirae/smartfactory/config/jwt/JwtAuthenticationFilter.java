package com.mirae.smartfactory.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirae.smartfactory.config.auth.PrincipalDetails;
import com.mirae.smartfactory.domain.resource.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter.attemptAuthentication 로그인 시도");

        //1. username, password 받아서
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            log.info("member = {}", member);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getLoginId(), member.getPassword());

            log.info("authenticationToken : ", authenticationToken.getName());

            //PrincipalDetailsService의 loadUserByUsername()이 실횅된 후 정상이면 authentication이 리턴됨.
            //DB에 있는 username과 password가 일치한다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info("authentication: {}", authentication);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("로그인 완료됨 : {}", principalDetails.getMember().getLoginId()); /// 로그인이 정상적으로 되었다는 뜻.

            //authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 하는것.
            //리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거임
            //굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없는데 단지 권한 처리때문에 session에 넣어준다.
            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("attemptAuthentication 로그인 시도 종료");

        return null;
    }

    // attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter.successfulAuthentication 실행됨: 인증이 완료된것임");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //RSA 방식이 아니고 Hash암호방식
        String jwtToken = JWT.create()
                .withSubject("mirae-token") //토큰이름 의미없음
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getMember().getMemberId())
                .withClaim("username", principalDetails.getMember().getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }


}
