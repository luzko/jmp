package com.epam.jmp.boot.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
public class ProfileLocalTest extends ProfileAbstractTest {
    @Override
    @Test
    void assertProfileDatabaseName() throws SQLException {
        assertEquals("jdbc:h2:mem:local",
                Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL());
    }
}
