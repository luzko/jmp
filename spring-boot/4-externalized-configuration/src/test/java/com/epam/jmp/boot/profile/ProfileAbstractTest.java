package com.epam.jmp.boot.profile;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public abstract class ProfileAbstractTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    abstract void assertProfileDatabaseName() throws SQLException;
}
