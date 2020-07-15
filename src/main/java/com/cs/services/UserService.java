package com.cs.services;

import com.cs.domain.dtos.UserDto;
import com.cs.entity.User;
import com.cs.exceptions.UserRegistrationException;

import java.util.List;

/**
 * Demo interface
 *
 * @author Zhao Shijie
 * @date 2019/08/12
 */
public interface UserService {
    void doRegister(UserDto userDto) throws UserRegistrationException;

    User getById(Long id);

    List<User> listUser();

    User getByName(String name);

    void insertUser(User user);
}
