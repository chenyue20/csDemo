package com.cs.security.provider;

import com.cs.domain.dtos.UserDto;
import com.cs.entity.User;
import com.cs.exceptions.UserInfoNotPrrovidException;
import com.cs.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static java.util.Objects.isNull;

/**
 * Demo class
 *
 * @author Albert
 * @date 2019/08/13
 */
@Component
public class LoginAuthticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = userService.getByName(username);
        if (isNull(user)) {
            throw new UserInfoNotPrrovidException(1004, "username not found");
        }
        //only for demo, actually we need to retrieve from DB to verify
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        //only for demo, we need to input the user roles
        return new UsernamePasswordAuthenticationToken(userDto, null, authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
