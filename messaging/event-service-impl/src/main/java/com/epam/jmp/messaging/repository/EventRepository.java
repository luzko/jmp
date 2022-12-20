package com.epam.jmp.messaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp.messaging.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
