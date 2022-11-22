package com.epam.jmp.boot.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.jmp.boot.serivce.PersonService;

@SpringBootTest
class PersonServiceIT {
    @Autowired
    private PersonService personService;

    @Test
    void migrationPersonsCountTest() {
        assertEquals(2, personService.findAll().size());
    }

    @Test
    void migrationPersonsByIdTest() {
        assertEquals("test2@gmail.com", personService.findById(2L).getEmail());
    }
}
