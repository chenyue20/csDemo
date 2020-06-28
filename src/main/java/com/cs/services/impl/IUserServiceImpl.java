package com.cs.services.impl;

import com.cs.entity.User;
import com.cs.repositories.UserMapper;
import com.cs.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
public class IUserServiceImpl implements IUserService {

    private final UserMapper mapper;

    @Override
    public List<User> listUser() {
        return mapper.listUser();
    }

    @Override
    public void insertUser(@RequestBody User request) {
        log.info(request.toString());
        mapper.insertUser(request);
    }


    @Override
    public User getById(Long id) {
        return mapper.getById(id);
    }
}
