package com.epam.jmp.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.jmp.boot.model.Error;
import com.epam.jmp.boot.model.Person;
import com.epam.jmp.boot.serivce.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    void findByIdTest() throws Exception {
        Person expected = new Person(1L, "test1", "test1@gmail.com");
        when(personService.findById(1L)).thenReturn(expected);
        String json = mockMvc
                .perform(get("/persons/" + 1L))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Person actual = objectMapper.readValue(json, Person.class);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdNotFoundTest() throws Exception {
        when(personService.findById(1L)).thenThrow(new NoSuchElementException("Person not found"));
        String json = mockMvc
                .perform(get("/persons/" + 1L))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("NOT_FOUND", error.getCode());
        assertEquals("Person not found", error.getMessage());
    }

    @Test
    void findByBadRequest() throws Exception {
        when(personService.findById(1L)).thenThrow(new IllegalArgumentException());
        String json = mockMvc
                .perform(get("/persons/" + 1L))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("BAD_REQUEST", error.getCode());
    }

    @Test
    void findByServerError() throws Exception {
        String json = mockMvc
                .perform(get("/persons/" + "test"))
                .andExpect(status().is5xxServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("INTERNAL_SERVER_ERROR", error.getCode());
    }
}
