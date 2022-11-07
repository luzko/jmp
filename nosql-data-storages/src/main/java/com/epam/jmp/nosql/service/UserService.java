package com.epam.jmp.nosql.service;

import java.util.List;
import java.util.Optional;

import com.epam.jmp.nosql.model.Sport;
import com.epam.jmp.nosql.model.User;

public interface UserService {
    User create(final User user);

    Optional<User> findById(final String id);

    Optional<User> findByEmail(final String email);

    Optional<User> addSport(String userId, Sport sport);

    List<User> findBySportName(final String sportName);
}
