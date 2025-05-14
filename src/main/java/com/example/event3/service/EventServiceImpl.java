package com.example.event3.service;

import com.example.event3.dto.RequestEventDto;
import com.example.event3.dto.ResponseEventDto;
import com.example.event3.entity.Event;
import com.example.event3.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    public EventServiceImpl( EventRepository eventRepository ) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEventDto createEventService( RequestEventDto dto ) {
        Event event = new Event( dto.getTitle(), dto.getContent(), dto.getName(), dto.getPw());

        LocalDateTime date = LocalDateTime.now();
        event.setCreateAndModifyDate(date);

        return eventRepository.saveEvent(event);
    }

    @Override//모든 일정 조회
    public List<ResponseEventDto> checkAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override//dto에서 이름을 불러와 이름이 동일할때만 리스트에 담아서 전달
    public List<ResponseEventDto> checkFiltering( String name, LocalDateTime mdifyDate ) {

        List<ResponseEventDto> allEvents = eventRepository.findAllEvents();
        List<ResponseEventDto> filteringList = new ArrayList<>();

        for ( ResponseEventDto dto : allEvents ) {
            if( name.equals( dto.getName() ) ) {
                filteringList.add( dto );
            }
        }
        return filteringList;
    }

    @Override
    public ResponseEventDto findEventById( Long id ) {
        Optional<Event> optionalEvent = eventRepository.findEventById(id);
        if (optionalEvent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id 가" + id + "인 일정을 찾지못했습니다");
        }
        return new ResponseEventDto(optionalEvent.get());
    }

    @Transactional
    @Override
    public ResponseEventDto updateEvent( Long id, String title, String content ) {
        if ( title == null || content == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목 혹은 할일의 값이 비어있습니다.");
        }
        int updatedRow = eventRepository.update(id, title, content);

        if ( updatedRow == 0 ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no data");
        }

        Optional<Event> optionalEvent = eventRepository.findEventById(id);

        return new ResponseEventDto(optionalEvent.get());
    }

    @Override
    public void deleteEvent( Long id ) {

        int deleteRow = eventRepository.deleteEvent(id);

        if( deleteRow == 0 ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 이벤트가 없습니다");
        }
    }

    @Override
    public ResponseEventDto updateTitle( Long id, String title ) {

        if ( title == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다");
        }
        int updateRow = eventRepository.updateTitle(id, title);

        if ( updateRow == 0 ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"찾을수 없습니다.");
        }
        Optional<Event> optionalEvent = eventRepository.findEventById(id);

        return new ResponseEventDto( optionalEvent.get() );
    }
}
