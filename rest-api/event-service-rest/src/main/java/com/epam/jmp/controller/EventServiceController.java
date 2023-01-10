package com.epam.jmp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.model.Event;
import com.epam.jmp.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Events")
public class EventServiceController {

    @Autowired
    private EventService eventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create event")
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update event")
    public void updateEvent(@PathVariable("id") Long eventId, @RequestBody Event event) {
        event.setId(eventId);
        eventService.updateEvent(event);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get event by id")
    public Event getEvent(@PathVariable("id") Long eventId) {
        return eventService.getEvent(eventId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete event by id")
    public void deleteEvent(@PathVariable("id") Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all events")
    public Collection<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/search", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiParam(name = "title", required = true)
    @ApiOperation(value = "Find all events by title")
    public Collection<Event> getAllEventsByTitle(@RequestParam("title") final String title) {
        return eventService.getAllEventsByTitle(title);
    }
}
