package com.epam.jmp.spring.dao.impl;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.epam.jmp.spring.dao.UserDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.model.to.UserTo;
import com.epam.jmp.spring.storage.InMemoryStorage;

import lombok.Setter;

@Repository
@Setter
public class UserDaoImpl implements UserDao {
    private InMemoryStorage inMemoryStorage;

    @Override
    public User getById(long id) {
        return getData().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User create(User user) {
        if (ofNullable(getById(user.getId())).isEmpty()) {
            getData().add(new UserTo(user.getId(), user.getName(), user.getEmail()));
            return user;
        }
        throw new AlreadyExistsException("User already exists");
    }

    @Override
    public User update(User user) {
        ofNullable(getById(user.getId()))
                .ifPresentOrElse(userTo -> {
                    userTo.setName(user.getName());
                    userTo.setEmail(user.getEmail());
                }, () -> {
                    throw new NotFoundException("User doesn't exist");
                });
        return user;
    }

    @Override
    public boolean deleteById(long id) {
        return getData().removeIf(user -> user.getId() == id);
    }

    @Override
    public User getUserByEmail(String email) {
        return getData().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> users = getData().stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
        return paginate(users, pageSize, pageNum);
    }

    private List<UserTo> getData() {
        return (List<UserTo>) inMemoryStorage.getData(UserTo.class);
    }
}
