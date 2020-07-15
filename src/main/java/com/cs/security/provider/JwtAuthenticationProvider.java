package com.cs.security.provider;

import com.cs.security.JwtAuthenticationToken;
import com.cs.security.jwt.JjwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getJwtToken();
        log.info("校验token："+token);
//        try {
//            Map<String, String> claimsMap =  JwtTokenUtil.verifyToken(token);
//        } catch (Exception e) {
//            throw new AuthenticationServiceException("Invalid token", e);
//        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        //just for demo, actually we can retrieve role data from claimsMap
        authorities.add(new SimpleGrantedAuthority("service_access"));
        return new JwtAuthenticationToken(authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
