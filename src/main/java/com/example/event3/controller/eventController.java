package com.example.event3.controller;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.dto.PutRequestEventDtd;
import com.example.event3.service.EventService;
import org.apache.tomcat.util.http.parser.HttpParser;
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
    public eventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<PostResponseEventDto> createEvent(@RequestBody PostRequestEventDto dto) {

        return new ResponseEntity<>(eventService.createEventService(dto), HttpStatus.CREATED);
    }

    //이벤트 전체조회 는 PostResponseEventDto와 동일한정보이므로 기존dto그대로사용
    @GetMapping
    public ResponseEntity<List<PostResponseEventDto>> checkAllEvent() {

        return new ResponseEntity<>(eventService.checkAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/filter")//이름으로 조회 filter?name=이름
    public ResponseEntity<List<PostResponseEventDto>> checkFiltering(
            @RequestParam(required = true) String name,
            @RequestParam(required = false) LocalDateTime modifyDate
    ) {
        return new ResponseEntity<>(eventService.checkFiltering(name, modifyDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseEventDto> checkEventbyId(@PathVariable Long id) {

        return new ResponseEntity<>(eventService.findEventById(id), HttpStatus.OK);
    }

    //id로 전체수정하기
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseEventDto> updateEvent(
            @PathVariable Long id,
            @RequestBody PutRequestEventDtd dto
    ) {
        return new ResponseEntity<>(eventService.updateEvnet(id, dto.getTitle(), dto.getThingsToDo()), HttpStatus.OK);
    }

    //id로 내용만 수정하기
    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseEventDto> updateThingsTo(
            @PathVariable Long id,
            @RequestBody PutRequestEventDtd dto
    ) {
        return new ResponseEntity<>(eventService.updateEvnet(id, dto.getTitle(), dto.getThingsToDo()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
