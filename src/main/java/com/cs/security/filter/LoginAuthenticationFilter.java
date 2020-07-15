package com.cs.security.filter;

import com.cs.domain.vo.LoginVO;
import com.cs.entity.User;
import com.cs.exceptions.UserInfoNotPrrovidException;
import com.cs.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cs.enums.ServiceErrorCodes.USERNAME_NOT_FOUND;
import static com.cs.enums.ServiceErrorCodes.USERNAME_OR_PASSWORD_NOT_PROVIDE;
import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Demo class
 *
 * @author Albert
 * @date 2019/08/13
 */
@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;


    public LoginAuthenticationFilter(String defaultFilterProcessesUrl,
                                     AuthenticationSuccessHandler successHandler,
                                     AuthenticationFailureHandler failureHandler,
                                     ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        LoginVO loginVO = objectMapper.readValue(request.getReader(), LoginVO.class);
        log.info("登录用户：{}", loginVO);
        if (isEmpty(loginVO.getName()) || isEmpty(loginVO.getPassword())) {
            throw new UserInfoNotPrrovidException(1004, "username not found");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginVO.getUsername(), loginVO.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
