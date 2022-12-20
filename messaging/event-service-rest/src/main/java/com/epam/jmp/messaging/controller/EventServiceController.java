package com.epam.jmp.messaging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.epam.jmp.messaging.dto.EventDto;
import com.epam.jmp.messaging.impl.EventServiceImpl;

@RestController
@RequestMapping("/api/events")
public class EventServiceController {

    @Autowired
    private EventServiceImpl service;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return new ResponseEntity<>(service.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getEvent(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(service.createEvent(eventDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDto> removeEvent(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteEvent(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto event, @PathVariable Long id) {
        return new ResponseEntity<>(service.updateEvent(id, event), HttpStatus.OK);
    }
}
