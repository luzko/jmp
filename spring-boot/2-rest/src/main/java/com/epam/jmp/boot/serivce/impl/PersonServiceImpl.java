package com.epam.jmp.boot.serivce.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.epam.jmp.boot.model.Person;
import com.epam.jmp.boot.repository.PersonRepository;
import com.epam.jmp.boot.serivce.PersonService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person findById(long id) {
        log.info("find by id: {}", id);
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    @Override
    public List<Person> findAll() {
        log.info("find all");
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        log.info("save: {}", person);
        return personRepository.save(person);
    }

    @Override
    public void delete(long id) {
        log.info("delete: {}", id);
        personRepository.deleteById(id);
    }
}
