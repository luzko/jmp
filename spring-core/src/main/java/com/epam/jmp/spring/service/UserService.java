package com.epam.jmp.spring.service;

import java.util.List;

import com.epam.jmp.spring.model.User;

public interface UserService {
    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUserById(long userId);
}
