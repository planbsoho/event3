package com.example.event3.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class EventRequestDto {
    private String title;
    private String content;
    private LocalDate date;
}
