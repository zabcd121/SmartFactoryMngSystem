package com.mirae.smartfactory.interceptor;

import com.mirae.smartfactory.consts.SessionConst;
import com.mirae.smartfactory.domain.model.resource.RoleType;
import com.mirae.smartfactory.dto.member.SimpleMemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminLoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("관리자 인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession(false);

        if (session == null) {
            log.info("session = null, 미인증 사용자 요청");

            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);

            return false;
        }

        SimpleMemberInfo loginMember = (SimpleMemberInfo) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null) {
            log.info("SessionMemberInfo = null, 미인증 사용자 요청");

            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);

            return false;
        }

        if(loginMember.getRoleType() != RoleType.ROLE_ADMIN) {
            return false;
        }

        return true;
    }

}
