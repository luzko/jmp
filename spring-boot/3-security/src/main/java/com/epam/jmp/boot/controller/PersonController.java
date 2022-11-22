package com.epam.jmp.boot.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.boot.model.Person;
import com.epam.jmp.boot.serivce.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.save(person));
    }

    @RolesAllowed("ADMIN")
    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
