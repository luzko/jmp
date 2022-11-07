package com.epam.jmp.nosql.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epam.jmp.nosql.model.Sport;
import com.epam.jmp.nosql.model.User;
import com.epam.jmp.nosql.repository.UserRepository;
import com.epam.jmp.nosql.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> addSport(String userId, Sport sport) {
        return userRepository.findById(userId).map(user -> {
            addSportToUser(user, sport);
            return userRepository.save(user);
        });
    }

    @Override
    public List<User> findBySportName(String sportName) {
        return userRepository.findBySportName(sportName);
    }

    public void addSportToUser(User user, Sport sport) {
        if (user.getSports() == null) {
            user.setSports(new HashSet<>());
        }
        user.getSports().add(sport);
    }
}
