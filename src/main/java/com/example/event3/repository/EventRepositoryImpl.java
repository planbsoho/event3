package com.example.event3.repository;

import com.example.event3.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

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
}
