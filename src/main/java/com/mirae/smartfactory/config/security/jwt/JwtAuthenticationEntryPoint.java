package com.mirae.smartfactory.config.security.jwt;

import com.mirae.smartfactory.consts.ConditionCode;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

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
            AuthenticationException authException) throws IOException {

        log.error("Responding with unauthorized error. Message - {}", authException.getMessage());

        Exception exception = (Exception) request.getAttribute("exception");

        log.error("exception: {}", exception.getMessage());

        if(exception.equals(null)){
            log.error("null");
            setResponse(response, ACCESS_DENIED);
        }
        else if(exception instanceof ExpiredJwtException) {
            log.error("jwt 만료");
            setResponse(response, EXPIRED_TOKEN);
        }
        else {
            log.error("else");
            setResponse(response, WRONG_TYPE_TOKEN);
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
