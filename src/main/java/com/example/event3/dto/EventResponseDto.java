package com.example.event3.dto;

import com.example.event3.entity.Event;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventResponseDto {

    private Long id;
    private String title;
    private String contents;
    public LocalDate date;
    public EventResponseDto(Event event){
        this.id = event.getId();
        this.title = event.getTitle();
        this.contents = event.getContents();
        this.date = event.getDate();
    }
}
