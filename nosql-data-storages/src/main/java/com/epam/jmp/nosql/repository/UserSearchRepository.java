package com.epam.jmp.nosql.repository;

import java.util.List;

import com.epam.jmp.nosql.model.User;

public interface UserSearchRepository {
    List<User> search(String query);
}
