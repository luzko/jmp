package com.epam.jmp.nosql.service;

import java.util.List;

import com.epam.jmp.nosql.model.User;

public interface UserSearchService {
    List<User> search(String query);
}
