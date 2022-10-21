package com.mirae.smartfactory.application.service;

import com.mirae.smartfactory.config.security.jwt.JwtTokenProvider;
import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.dto.TokensDto;
import com.mirae.smartfactory.dto.member.LoginResDto;
import com.mirae.smartfactory.dto.member.SimpleMemberInfo;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDetailsService myUserDetailsService;
    private final RedisTemplate redisTemplate;

    @Transactional
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
//        RefreshRedisToken token = RefreshRedisToken.createToken(loginId, refreshToken);

        // 기존 토큰이 있으면 수정, 없으면 생성
//        refreshRedisRepository.save(token);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), refreshToken, jwtTokenProvider.getExpiration(refreshToken), TimeUnit.MILLISECONDS);

        // accessToken과 refreshToken 리턴
        TokensDto tokenResponseDto = TokensDto.builder()
                .accessToken("Bearer " + jwtTokenProvider.createAccessToken(authentication))
                .refreshToken("Bearer " + refreshToken)
                .build();

        Member member = memberRepository.findByLoginId(loginId).orElseGet(null);
        log.info("#########member: {}", member.getMemberId());

        SimpleMemberInfo simpleMemberInfo = new SimpleMemberInfo(member.getMemberId(), member.getRoleType());

        return new LoginResDto(tokenResponseDto, simpleMemberInfo);

    }

    @Transactional
    public TokensDto reissueAccessToken(String refreshToken) {
        log.info("refresh: {}", refreshToken);

        //token 앞에 "Bearer " 제거
        String resolveToken = resolveToken(refreshToken);

        //토큰 검증 메서드
        //실패시 jwtTokenProvider.validateToken(resolveToken) 에서 exception을 리턴함
        jwtTokenProvider.validateToken(resolveToken);

        Authentication authentication = jwtTokenProvider.getAuthentication(resolveToken);
        // 디비에 있는게 맞는지 확인
//        RefreshRedisToken refreshRedisToken = refreshRedisRepository.findById(authentication.getName()).get();
//        log.info("refreshRedisToken = {}", refreshRedisToken);

        String findRefreshToken = (String) redisTemplate.opsForValue().get("RT:" + authentication.getName());

        // 토큰이 같은지 확인
        if(!resolveToken.equals(findRefreshToken)){
            throw new RuntimeException("not equals refresh token");
        }

        // 재발행해서 저장
        String newRefreshToken = jwtTokenProvider.createRefreshToken(authentication);
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), newRefreshToken, jwtTokenProvider.getExpiration(newRefreshToken), TimeUnit.MILLISECONDS);
//        RefreshRedisToken newRedisToken = RefreshRedisToken.createToken(authentication.getName(), newToken);
//        refreshRedisRepository.save(newRedisToken);

        // accessToken과 refreshToken 모두 재발행
        return TokensDto.builder()
                .accessToken("Bearer " + jwtTokenProvider.createAccessToken(authentication))
                .refreshToken("Bearer " + newRefreshToken)
                .build();
    }

    @Transactional
    public void logout(TokensDto tokensDto) {
        String resolveAccessToken = resolveToken(tokensDto.getAccessToken());
        // 1. access token 검증
        jwtTokenProvider.validateToken(resolveAccessToken);

        Authentication authentication = jwtTokenProvider.getAuthentication(resolveAccessToken);

        if(redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 남은 유효시간동안만 블랙리스트에 저장하고 그 뒤에는 자동으로 삭제되도록 하면 후에 해커가 탈취한 토큰으로 로그인을 시도하더라도 만료돼서 인증 실패함
        Long remainedExpiration = jwtTokenProvider.getExpiration(resolveAccessToken);
        redisTemplate.opsForValue()
                .set(resolveAccessToken, "logout", remainedExpiration, TimeUnit.MILLISECONDS);

    }

    //token 앞에 "Bearer-" 제거
    private String resolveToken(String token){
        log.info(token);
        if(token.startsWith("Bearer "))
            return token.substring(7);
        throw new RuntimeException("not valid refresh token !!");
    }
}
