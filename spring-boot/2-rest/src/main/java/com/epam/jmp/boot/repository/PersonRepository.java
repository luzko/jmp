package com.epam.jmp.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp.boot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
