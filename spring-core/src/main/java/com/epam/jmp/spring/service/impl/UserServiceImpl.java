package com.epam.jmp.spring.service.impl;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.jmp.spring.dao.UserDao;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User getUserById(long userId) {
        return ofNullable(userDao.getById(userId))
                .orElseThrow(() -> new NotFoundException("User doesn't exist"));
    }

    @Override
    public User getUserByEmail(String email) {
        return ofNullable(userDao.getUserByEmail(email))
                .orElseThrow(() -> new NotFoundException("User doesn't exist"));
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new IllegalArgumentException("invalid page size or pageNum");
        }
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUserById(long userId) {
        return userDao.deleteById(userId);
    }
}
