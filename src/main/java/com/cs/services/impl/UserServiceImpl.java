package com.cs.services.impl;

import com.cs.domain.dtos.UserDto;
import com.cs.entity.User;
import com.cs.enums.ServiceErrorCodes;
import com.cs.exceptions.UserRegistrationException;
import com.cs.repositories.UserRepository;
import com.cs.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void doRegister(UserDto userDto) throws UserRegistrationException {
        if (userDto == null) {
            throw new UserRegistrationException(ServiceErrorCodes.REGISTRATION_INFO_NULL);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.insertUser(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> listUser() {
        return userRepository.listUser();
    }

    @Override
    public void insertUser(User user) {
        userRepository.insertUser(user);
    }
}
