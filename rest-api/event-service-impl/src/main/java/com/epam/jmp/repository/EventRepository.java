package com.epam.jmp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findAllByTitle(String title);
}
