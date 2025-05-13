package com.example.event3.service;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.entity.Event;
import com.example.event3.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

//    public EventService (EventRepository eventRepository){
//        this.eventRepository = eventRepository;
//    }  선언은 인터페이스 구현은 임플?

    public EventServiceImpl( EventRepository eventRepository ) {
        this.eventRepository = eventRepository;
    }

    @Override
    public PostResponseEventDto createEventService( PostRequestEventDto dto ) {
        Event event = new Event( dto.getTitle(), dto.getThingsToDo(), dto.getName(), dto.getPw() );

        LocalDateTime date = LocalDateTime.now();
        event.setCreateAndModifyDate(date);

        Event savedEvent = eventRepository.saveEvent( event );
        return new PostResponseEventDto(savedEvent);
    }

    @Override//모든 일정 조회
    public List<PostResponseEventDto> checkAllEvents() {

        return eventRepository.findAllEvents();
    }

    @Override//dto에서 이름을 불러와 이름이 동일할때만 리스트에 담아서 전달
    public List<PostResponseEventDto> checkFiltering(String name, LocalDateTime mdifyDate) {

        List<PostResponseEventDto> allEvents = eventRepository.findAllEvents();
        List<PostResponseEventDto> filteringList = new ArrayList<>();

        for ( PostResponseEventDto dto : allEvents ) {
            if( name.equals( dto.getName() ) ) {
                filteringList.add(dto);
            }
        }
        return filteringList;
    }

    @Override
    public PostResponseEventDto findEventById(Long id) {
        Event event = eventRepository.findEventById(id);
        if ( event == null ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id 가" + id + "인 일정을 찾지못했습니다");
        }
        return new PostResponseEventDto(event);
    }
}
