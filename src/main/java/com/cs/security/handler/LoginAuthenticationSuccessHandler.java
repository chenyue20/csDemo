package com.cs.security.handler;

import com.cs.domain.dtos.UserDto;
import com.cs.security.jwt.JjwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;
import static java.util.Objects.isNull;

/**
 * Demo class
 *
 * @author Albert
 * @date 2019/08/13
 */
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        //To generate the JWT and add it in response header
        UserDto userDto = (UserDto) authentication.getPrincipal();
        String username = userDto.getUsername();
        String taken = JjwtUtil.createJWT(username);

        Map<String, String> map = new HashMap<>();
        map.put("taken", taken);
        map.put("loginName", username);
        map.put("status", "success");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        log.info("登录成功");
        clearAuthenticationSession(request);
        OutputStream out = response.getOutputStream();
        out.write(new ObjectMapper().writeValueAsString(map).getBytes());
        out.flush();
        out.close();
//        objectMapper.writeValue(response.getWriter(), map);
    }

    public final void clearAuthenticationSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
