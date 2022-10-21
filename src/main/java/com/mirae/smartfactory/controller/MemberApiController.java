package com.mirae.smartfactory.controller;

//import com.mirae.smartfactory.config.security.jwt.JwtProperties;
//import com.mirae.smartfactory.config.jwt.JwtTokenProvider;
//import com.mirae.smartfactory.config.security.jwt.JwtUtil;
import com.mirae.smartfactory.dto.TokensDto;
import com.mirae.smartfactory.dto.member.LoginResDto;
import com.mirae.smartfactory.dto.member.MemberLoginDto;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.mirae.smartfactory.consts.ConditionCode.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public SuccessResult<LoginResDto> login(@Validated @RequestBody MemberLoginDto memberLoginDto, HttpServletResponse response) {
        LoginResDto loginRes = memberService.signIn(memberLoginDto.getLoginId(), memberLoginDto.getPassword());
//
//
//        String authToken = jwtUtil.makeAuthToken(loginMember);
//        response.addHeader(JwtProperties.HEADER_AUTHORIZATION, JwtProperties.TOKEN_PREFIX + authToken);
//
//        SimpleMemberInfo sessionMemberInfo = new SimpleMemberInfo(loginMember.getMemberId(), loginMember.getRoleType());

        return new SuccessResult<LoginResDto>(LOGIN_SUCCESS.getCode(), LOGIN_SUCCESS.getMessage(), loginRes);

    }

    @DeleteMapping("/logout")
    public SuccessNoResult logout(@RequestBody TokensDto tokenDto) {
        memberService.logout(tokenDto);
        return new SuccessNoResult(LOGOUT_SUCCESS.getCode(), LOGOUT_SUCCESS.getMessage());
    }

    /**
     * Access token이 만료되었을 경우 프론트에서 요청할 api
     * @param refreshToken : Refresh token을 입력받는다.
     * @param refreshToken : Refresh token을 입력받는다.
     * @return TokenResponseDto : Access token과 Refresh token 모두 재발급해준다.
     */
    @PostMapping("/reissue")
    public TokensDto reissueAccessToken(@RequestParam String refreshToken){
        return memberService.reissueAccessToken(refreshToken);
    }
}
