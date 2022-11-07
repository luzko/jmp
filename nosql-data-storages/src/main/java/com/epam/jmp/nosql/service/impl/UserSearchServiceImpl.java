package com.epam.jmp.nosql.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.jmp.nosql.model.User;
import com.epam.jmp.nosql.repository.UserSearchRepository;
import com.epam.jmp.nosql.service.UserSearchService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {
    private final UserSearchRepository userSearchRepository;

    @Override
    public List<User> search(String query) {
        return userSearchRepository.search(query);
    }
}
