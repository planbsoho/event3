package com.example.event3.service;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public interface EventService {
    PostResponseEventDto createEventService( PostRequestEventDto dto );
    List<PostResponseEventDto> checkAllEvents();
    List<PostResponseEventDto> checkFiltering(String name, LocalDateTime mdifyDate);
    PostResponseEventDto findEventById(Long id);
}
