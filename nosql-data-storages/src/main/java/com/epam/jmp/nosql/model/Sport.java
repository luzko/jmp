package com.epam.jmp.nosql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sport {
    private String id;
    private String sportName;
    private SportProficiency sportProficiency;
}
