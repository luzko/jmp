package com.epam.jmp.spring.dao;

import java.util.List;

import com.epam.jmp.spring.model.User;

public interface UserDao extends BaseDao<User>, CrudDao<User> {
    @Override
    User getById(long id);

    @Override
    User create(User obj);

    @Override
    User update(User obj);

    @Override
    boolean deleteById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);
}
