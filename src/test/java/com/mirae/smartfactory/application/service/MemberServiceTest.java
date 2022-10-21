package com.mirae.smartfactory.application.service;

import com.mirae.smartfactory.config.security.jwt.JwtTokenProvider;
import com.mirae.smartfactory.dto.TokensDto;
import com.mirae.smartfactory.dto.member.LoginResDto;
import com.mirae.smartfactory.repository.MemberRepository;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
//@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원_로그인(){

        //given
        String loginId = "admin1234";
        String password = "1234";

        //when
        LoginResDto loginResDto = memberService.signIn(loginId, password);


        //then
        System.out.println("loginResDto.getMemberInfo().getMemberId() = " + loginResDto.getMemberInfo().getMemberId());
        System.out.println("loginResDto.getTokenInfo().getAccessToken() = " + loginResDto.getTokenInfo().getAccessToken());
        System.out.println("loginResDto.getTokenInfo().getRefreshToken() = " + loginResDto.getTokenInfo().getRefreshToken());
    }

    @Test
    void 회원_로그아웃() {

        //given
        String loginId = "admin1234";
        String password = "1234";
        LoginResDto loginResDto = memberService.signIn(loginId, password);

        TokensDto tokensDto = new TokensDto(loginResDto.getTokenInfo().getAccessToken(), loginResDto.getTokenInfo().getRefreshToken());


        //when
        memberService.logout(tokensDto);

        //then
    }
}