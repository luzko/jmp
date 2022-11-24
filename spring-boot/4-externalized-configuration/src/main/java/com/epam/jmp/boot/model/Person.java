package com.epam.jmp.boot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
}
