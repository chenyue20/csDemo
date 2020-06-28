package com.cs.repositories;


import com.cs.entity.User;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhao Shijie
 * @date 2019/08/12
 */
public interface UserRepository {
    User getById(Long id);

    List<com.cs.entity.User> listUser();

    void insertUser(User user);
}
