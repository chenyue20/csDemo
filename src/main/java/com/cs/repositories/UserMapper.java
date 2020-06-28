package com.cs.repositories;


import com.cs.entity.User;

import java.util.List;

public interface UserMapper {

    User getById(Long id);

    List<User> listUser();

    void insertUser(User user);
}
