package com.example.event3.controller;

import com.example.event3.dto.EventRequestDto;
import com.example.event3.dto.EventResponseDto;
import com.example.event3.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/events")
public class eventController {
    private final Map<Long, Event> eventList = new TreeMap<>();

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto dto){
        //식별자가 1씩증가
        Long eventId = eventList.isEmpty()? 1 : Collections.max(eventList.keySet())+1;

        Event event = new Event(eventId, dto.getTitle(), dto.getContent(), dto.getDate());

        eventList.put(eventId, event);

        return new ResponseEntity<>(new EventResponseDto(event), HttpStatus.CREATED);
    }
}
