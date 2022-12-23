package com.epam.jmp.messaging.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.epam.jmp.messaging.model.EventType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto extends RepresentationModel<EventDto> {

    private Long id;
    private String title;
    private String place;
    private String speaker;
    private EventType eventType;
    private LocalDateTime dateTime;
}
