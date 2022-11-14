package com.epam.jmp.spring.model.to;

import com.epam.jmp.spring.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTo implements User {
    private long id;
    private String name;
    private String email;
}
