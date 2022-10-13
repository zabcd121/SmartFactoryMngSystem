package com.mirae.smartfactory.application.service;

import com.mirae.smartfactory.config.redis.RefreshRedisRepository;
import com.mirae.smartfactory.config.redis.RefreshRedisToken;
import com.mirae.smartfactory.config.security.jwt.JwtTokenProvider;
import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.dto.TokenResponseDto;
import com.mirae.smartfactory.dto.member.LoginResDto;
import com.mirae.smartfactory.dto.member.SimpleMemberInfo;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDetailsService myUserDetailsService;
    private final RefreshRedisRepository refreshRedisRepository;

    @Transactional(readOnly = true)
    public LoginResDto signIn(String loginId, String password) {
        // uesrId 확인
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginId);

        // pw 확인
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException(userDetails.getUsername() + " Invalid password");
        }

        Authentication authentication =  new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        // refresh token 발급 및 저장
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication);
        RefreshRedisToken token = RefreshRedisToken.createToken(loginId, refreshToken);

        // 기존 토큰이 있으면 수정, 없으면 생성
        refreshRedisRepository.save(token);

        // accessToken과 refreshToken 리턴
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken("Bearer " + jwtTokenProvider.createAccessToken(authentication))
                .refreshToken("Bearer " + refreshToken)
                .build();

        Member member = memberRepository.findByLoginId(loginId).orElseGet(null);
        log.info("#########member: {}", member.getMemberId());

        SimpleMemberInfo simpleMemberInfo = new SimpleMemberInfo(member.getMemberId(), member.getRoleType());

        return new LoginResDto(tokenResponseDto, simpleMemberInfo);

    }

    @Transactional(readOnly = true)
    public TokenResponseDto reissueAccessToken(String refreshToken) {
        log.info("refresh: {}", refreshToken);

        //token 앞에 "Bearer " 제거
        String resolveToken = resolveToken(refreshToken);

        //토큰 검증 메서드
        //실패시 jwtTokenProvider.validateToken(resolveToken) 에서 exception을 리턴함
        jwtTokenProvider.validateToken(resolveToken);

        Authentication authentication = jwtTokenProvider.getAuthentication(resolveToken);
        // 디비에 있는게 맞는지 확인
        RefreshRedisToken refreshRedisToken = refreshRedisRepository.findById(authentication.getName()).get();

        // 토큰이 같은지 확인
        if(!resolveToken.equals(refreshRedisToken.getToken())){
            throw new RuntimeException("not equals refresh token");
        }

        // 재발행해서 저장
        String newToken = jwtTokenProvider.createRefreshToken(authentication);
        RefreshRedisToken newRedisToken = RefreshRedisToken.createToken(authentication.getName(), newToken);
        refreshRedisRepository.save(newRedisToken);

        // accessToken과 refreshToken 모두 재발행
        return TokenResponseDto.builder()
                .accessToken("Bearer "+jwtTokenProvider.createAccessToken(authentication))
                .refreshToken("Bearer "+newToken)
                .build();
    }

    //token 앞에 "Bearer-" 제거
    private String resolveToken(String token){
        log.info(token);
        if(token.startsWith("Bearer "))
            return token.substring(7);
        throw new RuntimeException("not valid refresh token !!");
    }
}
