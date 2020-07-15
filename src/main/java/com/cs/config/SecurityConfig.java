package com.cs.config;

import com.cs.security.*;
import com.cs.security.filter.JwtAuthorizationTokenFilter;
import com.cs.security.filter.LoginAuthenticationFilter;
import com.cs.security.handler.LoginAuthenticationFailureHandler;
import com.cs.security.handler.LoginAuthenticationSuccessHandler;
import com.cs.security.provider.JwtAuthenticationProvider;
import com.cs.security.provider.LoginAuthticationProvider;
import com.cs.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String USER_LOGIN_URL = "/api/security/login";
    private final static String USER_REGISTER_URL = "/api/users/register";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private LoginAuthticationProvider loginAuthticationProvider;

    @Autowired private LoginAuthenticationSuccessHandler successHandler;
    @Autowired private LoginAuthenticationFailureHandler failureHandler;
    @Autowired private UserService userService;
    @Autowired private ObjectMapper objectMapper;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected LoginAuthenticationFilter buildLoginAuthenticationFilter(String defaultProcessUrl) {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(defaultProcessUrl,
                successHandler, failureHandler,objectMapper);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return loginAuthenticationFilter;
    }

    protected JwtAuthorizationTokenFilter buildJwtAuthorizationTokenFilter(List<String> pathsToSkip, String pattern) {
        SkipPathRequestMatcher requestMatcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter = new JwtAuthorizationTokenFilter(requestMatcher,failureHandler);
        jwtAuthorizationTokenFilter.setAuthenticationManager(authenticationManager);
        return jwtAuthorizationTokenFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllEndpointList = Arrays.asList(
                USER_LOGIN_URL,
                USER_REGISTER_URL
        );
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/api")
                    .authenticated()
                .and()
//                    .addFilterBefore(buildLoginAuthenticationFilter(USER_LOGIN_URL), UsernamePasswordAuthenticationFilter.class)
//                    .addFilterBefore(buildJwtAuthorizationTokenFilter(permitAllEndpointList, "/api/**"), UsernamePasswordAuthenticationFilter.class);
                    .addFilterBefore(buildLoginAuthenticationFilter(USER_LOGIN_URL), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthticationProvider);
//        auth.authenticationProvider(jwtAuthenticationProvider);
    }

}
