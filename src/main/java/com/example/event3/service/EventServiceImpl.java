package com.example.event3.service;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.entity.Event;
import com.example.event3.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

//    public EventService (EventRepository eventRepository){
//        this.eventRepository = eventRepository;
//    }  선언은 인터페이스 구현은 임플?

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public PostResponseEventDto createEventService(PostRequestEventDto dto){
        Event event = new Event( dto.getTitle(), dto.getThingsToDo(), dto.getName(), dto.getPw());

        LocalDateTime date = LocalDateTime.now();
        event.setCreateAndModifyDate(date);

        Event savedEvent = eventRepository.saveEvent(event);
        return new PostResponseEventDto(savedEvent);
    }
}
