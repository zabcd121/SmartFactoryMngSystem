package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.config.jwt.JwtProperties;
import com.mirae.smartfactory.config.jwt.JwtTokenProvider;
import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.dto.member.MemberLoginDto;
import com.mirae.smartfactory.dto.member.SimpleMemberInfo;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mirae.smartfactory.consts.ConditionCode.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public SuccessResult<SimpleMemberInfo> login(@Validated @RequestBody MemberLoginDto memberLoginDto, HttpServletResponse response) {
        Member loginMember = memberService.login(memberLoginDto.getLoginId(), memberLoginDto.getPassword());

        SimpleMemberInfo sessionMemberInfo = new SimpleMemberInfo(loginMember.getMemberId(), loginMember.getRoleType());

        String jwtToken = jwtTokenProvider.createToken(String.valueOf(loginMember.getMemberId()), loginMember.getRoleType().toString());
        response.addHeader(JwtProperties.HEADER_AUTHORIZATION, JwtProperties.TOKEN_PREFIX + jwtToken);

        return new SuccessResult<SimpleMemberInfo>(LOGIN_SUCCESS.getCode(), LOGIN_SUCCESS.getMessage(), sessionMemberInfo);

    }

    @DeleteMapping("/logout")
    public SuccessNoResult logout() {
        return new SuccessNoResult(LOGOUT_SUCCESS.getCode(), LOGOUT_SUCCESS.getMessage());
    }
}
