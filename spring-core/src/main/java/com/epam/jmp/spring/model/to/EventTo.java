package com.epam.jmp.spring.model.to;

import java.util.Date;

import com.epam.jmp.spring.model.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTo implements Event {
    private long id;
    private String title;
    private Date date;
}
