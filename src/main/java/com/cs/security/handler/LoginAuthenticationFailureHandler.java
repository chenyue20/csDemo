package com.cs.security.handler;

import com.cs.exceptions.UserInfoNotPrrovidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author Albert
 * @date 2019/08/13
 */
@Component
@Slf4j
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        map.put("status", "fail");
        if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            //TODO throw the exception and let ExceptionHandler to process it.
        }
        if (exception instanceof UserInfoNotPrrovidException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            map.put("message", exception.getMessage());
            //TODO throw the exception and let ExceptionHandler to process it.
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), map);
        log.info("登录失败");
    }
}
