package com.example.event3.repository;

import com.example.event3.dto.ResponseEventDto;
import com.example.event3.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    ResponseEventDto saveEvent( Event event );

    List<ResponseEventDto> findAllEvents();

    Optional<Event> findEventById( Long id );

    int update( Long id, String title, String contents );

    int updateTitle( Long id, String Title );

    int deleteEvent( long id );

    Event findEventByIdOrElseThrow( Long id );
}
