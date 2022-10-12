package com.mirae.smartfactory.config.jwt;

import com.mirae.smartfactory.consts.ConditionCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mirae.smartfactory.consts.ConditionCode.*;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

//        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");
//
//        request.setAttribute("response.failure.code", unAuthorizationCode.name());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, unAuthorizationCode.message());
        String exception = (String)request.getAttribute("exception");
        log.error("exception: {}", exception);

        if(exception.equals(null)) {
            setResponse(response, UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception.equals(EXPIRED_TOKEN.getCode())) {
            setResponse(response, EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, UNSUPPORTED_TOKEN);
        }
        else {
            setResponse(response, ACCESS_DENIED);
        }
    }

    private void setResponse(HttpServletResponse response, ConditionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("code", exceptionCode.getCode());
        responseJson.put("message", exceptionCode.getMessage());


        response.getWriter().print(responseJson);
    }
}
