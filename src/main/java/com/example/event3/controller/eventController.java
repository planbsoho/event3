package com.example.event3.controller;

import com.example.event3.dto.RequestEventDto;
import com.example.event3.dto.ResponseEventDto;
import com.example.event3.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class eventController {
    //필드
    private final EventService eventService;

    //생성자주입
    public eventController( EventService eventService ) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ResponseEventDto> createEvent( @RequestBody RequestEventDto dto ) {

        return new ResponseEntity<>( eventService.createEventService(dto), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<ResponseEventDto>> checkAllEvent() {

        return new ResponseEntity<>(eventService.checkAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ResponseEventDto>> checkFiltering(
            @RequestParam(required = true) String name,
            @RequestParam(required = false) LocalDateTime modifyDate
    ) {
        return new ResponseEntity<>( eventService.checkFiltering(name, modifyDate), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventDto> checkEventbyId( @PathVariable Long id ) {

        return new ResponseEntity<>( eventService.findEventById(id), HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDto> updateEvent(
            @PathVariable Long id,
            @RequestBody ResponseEventDto dto
    ) {
        return new ResponseEntity<>( eventService.updateEvent(id, dto.getTitle(), dto.getContent()), HttpStatus.OK );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseEventDto> updateTitle(
            @PathVariable Long id,
            @RequestBody ResponseEventDto dto
    ) {
        return new ResponseEntity<>( eventService.updateTitle(id, dto.getTitle()), HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent( @PathVariable Long id ) {
        eventService.deleteEvent(id);

        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}
