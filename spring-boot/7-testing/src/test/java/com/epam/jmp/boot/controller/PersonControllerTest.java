package com.epam.jmp.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.jmp.boot.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:initdata.sql")
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getPersonById() throws Exception {
        Person expected = new Person(1L, "test1", "test1@gmail.com");
        var json = mockMvc
                .perform(get("/persons/" + 1L))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Person actual = objectMapper.readValue(json, Person.class);
        assertEquals(expected, actual);
    }
}
