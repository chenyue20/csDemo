package com.cs.services;

import com.cs.entity.User;

import java.util.List;

public interface IUserService {
    List<User> listUser();

    void insertUser(User request);

    User getById(Long id);
}
