package com.epam.jmp.react.sportservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(value = "sports")
public class Sport {
    @Id
    private Long id;

    private String name;

    public Sport(String name) {
        this.name = name;
    }
}
