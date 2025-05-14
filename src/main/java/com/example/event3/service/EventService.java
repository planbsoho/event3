package com.example.event3.service;

import com.example.event3.dto.RequestEventDto;
import com.example.event3.dto.ResponseEventDto;
import com.example.event3.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    ResponseEventDto createEventService( RequestEventDto dto );

    List<ResponseEventDto> checkAllEvents();

    List<ResponseEventDto> checkFiltering( String name, LocalDateTime mdifyDate );

    ResponseEventDto findEventById( Long id );

    ResponseEventDto updateEvent( Long id, String title, String content );

    void deleteEvent( Long id );

    ResponseEventDto updateTitle( Long id, String title );

}
