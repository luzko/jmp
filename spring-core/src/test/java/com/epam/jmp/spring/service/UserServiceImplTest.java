package com.epam.jmp.spring.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.epam.jmp.spring.dao.UserDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.model.to.UserTo;
import com.epam.jmp.spring.service.impl.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserByIdTest() {
        long userId = 1;
        User expected = new UserTo(userId, "user", "user@gmail.com");
        when(userDao.getById(userId)).thenReturn(expected);
        User actual = userService.getUserById(userId);
        assertEquals(expected, actual);
    }

    @Test
    void getUserByIdNotFoundExceptionTest() {
        long userId = 1;
        when(userDao.getById(1)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void getUserByEmailTest() {
        String email = "user@gmail.com";
        User expected = new UserTo(1, "user", email);
        when(userDao.getUserByEmail(email)).thenReturn(expected);
        User actual = userService.getUserByEmail(email);
        assertEquals(expected, actual);
    }

    @Test
    void getUserByEmailNotFoundExceptionTest() {
        String email = "user@gmail.com";
        when(userDao.getUserByEmail(email)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> userService.getUserByEmail(email));
    }

    @Test
    void getUsersByNameTest() {
        String name = "user";
        User expected = new UserTo(1, name, "user@gmail.com");
        when(userDao.getUsersByName(name, 1, 1)).thenReturn(singletonList(expected));
        List<User> actualUsers = userService.getUsersByName(name, 1, 1);
        assertEquals(1, actualUsers.size());
        assertEquals(expected, actualUsers.get(0));
    }

    @Test
    void getUsersByNameIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUsersByName("user", -1, -1));
    }

    @Test
    void createUserTest() {
        User expected = new UserTo(1, "user", "user@gmail.com");
        when(userDao.create(expected)).thenReturn(expected);
        User actual = userService.createUser(expected);
        assertEquals(expected, actual);
    }

    @Test
    void createUserAlreadyExistsExceptionTest() {
        when(userDao.create(any())).thenThrow(AlreadyExistsException.class);
        assertThrows(AlreadyExistsException.class, () -> userService.createUser(any()));
    }

    @Test
    void updateUserTest() {
        User expected = new UserTo(1, "user", "user@gmail.com");
        when(userDao.update(expected)).thenReturn(expected);
        User actual = userService.updateUser(expected);
        assertEquals(expected, actual);
    }

    @Test
    void updateUserNotFoundExceptionTest() {
        when(userDao.update(any())).thenThrow(AlreadyExistsException.class);
        assertThrows(AlreadyExistsException.class, () -> userService.updateUser(any()));
    }

    @Test
    void deleteUserByIdTrueTest() {
        when(userDao.deleteById(1)).thenReturn(true);
        assertTrue(userService.deleteUserById(1));
    }

    @Test
    void deleteUserByIdFalseTest() {
        when(userDao.deleteById(1)).thenReturn(false);
        assertFalse(userService.deleteUserById(1));
    }
}
