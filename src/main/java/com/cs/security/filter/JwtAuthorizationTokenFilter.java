package com.cs.security.filter;

import com.cs.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;

    public JwtAuthorizationTokenFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public JwtAuthorizationTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                       AuthenticationFailureHandler authenticationFailureHandler
    ) {
        super(requiresAuthenticationRequestMatcher);
        this.authenticationFailureHandler=authenticationFailureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("No JWT token found in request headers");
        }
        String authToken = header.substring(7);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authToken);
        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                                        AuthenticationException failed) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request,response,failed);
    }
}
