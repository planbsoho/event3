package com.example.event3.entity;

import com.example.event3.dto.EventRequestDto;
import com.example.event3.dto.EventResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class Event {
    private Long id;
    private String title;
    private String contents;
    private LocalDate date;

    public void update(EventRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContent();
    }
    public void updateTitle(EventRequestDto dto){
        this.title = dto.getTitle();
    }
}
