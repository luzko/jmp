package com.epam.jmp.boot.serivce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.epam.jmp.boot.model.Person;
import com.epam.jmp.boot.repository.PersonRepository;
import com.epam.jmp.boot.serivce.impl.PersonServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void findByIdTest() {
        Person expected = new Person(1L, "test", "test@gmail.com");
        when(personRepository.findById(1L)).thenReturn(Optional.of(expected));
        Person actual = personService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdNoSuchElementExceptionTest() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> personService.findById(1L));
    }

    @Test
    void findAllTest() {
        List<Person> expected = List.of(new Person(1L, "test", "test@gmail.com"));
        when(personRepository.findAll()).thenReturn(expected);
        List<Person> actual = personService.findAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    void saveTest() {
        Person expected = new Person(1L, "test", "test@gmail.com");
        when(personRepository.save(expected)).thenReturn(expected);
        Person actual = personService.save(expected);
        assertEquals(expected, actual);
    }
}
