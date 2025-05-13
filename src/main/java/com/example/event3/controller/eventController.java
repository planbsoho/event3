package com.example.event3.controller;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class eventController {
    //필드
    private final EventService eventService;
    //생성자주입
    public eventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<PostResponseEventDto> createEvent(@RequestBody PostRequestEventDto dto){

        return new ResponseEntity<>(eventService.createEventService(dto), HttpStatus.CREATED);
    }
}
