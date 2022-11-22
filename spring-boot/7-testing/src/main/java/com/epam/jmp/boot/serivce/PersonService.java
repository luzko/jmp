package com.epam.jmp.boot.serivce;

import java.util.List;

import com.epam.jmp.boot.model.Person;

public interface PersonService {
    Person findById(long id);

    List<Person> findAll();

    Person save(Person person);

    void delete(long id);
}
