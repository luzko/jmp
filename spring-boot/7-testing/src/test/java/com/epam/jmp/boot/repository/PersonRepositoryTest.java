package com.epam.jmp.boot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.epam.jmp.boot.model.Person;

@ActiveProfiles("test")
@Sql(scripts = "classpath:initdata.sql")
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void findByIdTest() {
        Person person = new Person(1L, "test1", "test1@gmail.com");
        assertEquals(person, personRepository.findById(1L).orElse(null));
    }

    @Test
    void findAll() {
        Person person1 = new Person(1L, "test1", "test1@gmail.com");
        Person person2 = new Person(2L, "test2", "test2@gmail.com");
        assertIterableEquals(List.of(person1, person2), personRepository.findAll());
    }

    @Test
    void create() {
        Person person = Person.builder()
                .name("test3")
                .email("test3@gmail.com")
                .build();
        Person save = personRepository.save(person);
        assertEquals(3, personRepository.findAll().size());
        assertEquals(save, personRepository.findById(3L).orElse(null));
    }

    @Test
    void update() {
        Person person = new Person(2L, "update2", "update2@gmail.com");
        personRepository.save(person);
        assertEquals(person, personRepository.findById(2L).orElse(null));
    }
}
