package com.example.event3.repository;

import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventRepositoryImpl implements EventRepository{


    private final Map<Long, Event> eventList = new TreeMap<>();

    @Override
    public Event saveEvent(Event event) {
        Long eventId = eventList.isEmpty() ? 1 : Collections.max(eventList.keySet()) + 1;
        event.setId(eventId);

        eventList.put(eventId, event);
        return event;
    }

    @Override//새로운 리스트에 모든이벤트를 담아서 전달
    public List<PostResponseEventDto> findAllEvents() {
        List<PostResponseEventDto> allEvents = new ArrayList<>();

        for ( Event event : eventList.values() ) {
            PostResponseEventDto dto = new PostResponseEventDto(event);
            allEvents.add(dto);
        }
        return allEvents;
    }
}
