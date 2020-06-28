package com.cs.services;

import com.cs.domain.dtos.UserDto;
import com.cs.exceptions.UserRegistrationException;

/**
 * Demo interface
 *
 * @author Zhao Shijie
 * @date 2019/08/12
 */
public interface UserService {
    void doRegister(UserDto userDto) throws UserRegistrationException;
}
