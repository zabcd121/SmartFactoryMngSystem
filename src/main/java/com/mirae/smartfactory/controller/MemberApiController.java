package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.consts.SessionConst;
import com.mirae.smartfactory.domain.resource.Member;
import com.mirae.smartfactory.dto.member.MemberLoginDto;
import com.mirae.smartfactory.dto.member.SessionMemberInfo;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/login")
    public SuccessResult<SessionMemberInfo> login(@Validated @RequestBody MemberLoginDto memberLoginDto, HttpServletRequest request) {
        Member loginMember = memberService.login(memberLoginDto.getLoginId(), memberLoginDto.getPassword());

        SessionMemberInfo sessionMemberInfo = new SessionMemberInfo(loginMember.getMemberId(), loginMember.getRoleType());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, sessionMemberInfo);

        return new SuccessResult<SessionMemberInfo>(LOGIN_SUCCESS, "ok", sessionMemberInfo);

    }

    @PostMapping("/logout")
    public SuccessNoResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate(); //세션 제거
        }

        return new SuccessNoResult(LOGOUT_SUCCESS, "ok");
    }
}
