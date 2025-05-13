package com.example.event3.repository;

import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.entity.Event;

import java.util.List;

public interface EventRepository {
    PostResponseEventDto saveEvent(Event event);

    List<PostResponseEventDto> findAllEvents();

    Event findEventById(Long id);

    void deleteEvent(Long id);
}
