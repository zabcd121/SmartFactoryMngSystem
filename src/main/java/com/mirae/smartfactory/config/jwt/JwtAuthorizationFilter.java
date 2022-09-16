package com.mirae.smartfactory.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mirae.smartfactory.config.auth.PrincipalDetails;
import com.mirae.smartfactory.domain.resource.Member;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

// 시큐리티가 filter가지고 있는 그 필터중에 BasicAuthenticationFilter라는 것이 있음.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
//만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 안탐.
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    //인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader = " + jwtHeader);

        //header가 있는 확인
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        //JWT 토큰을 검증해서 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        String loginId
                = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString(); //JwtAuthenticationFilter에서 시크릿 키를 ~로 했음.


        //null이 아니면 서명이 정상적으로 된것임
        if(loginId != null) {
            Member memberEntity = memberRepository.findByLoginId(loginId).get();

            PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);

            // 위에 메서드에서는 로그인을 해서 authentication을 받아왔는데 이 메서드에서는 강제로 만드는거임
            // 근거는 username이 null이 아니기 때문에임
            // passwor를 null로 줘서 강제로 얻음
            //Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
