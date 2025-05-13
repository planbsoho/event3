package com.example.event3.service;

import com.example.event3.dto.PostRequestEventDto;
import com.example.event3.dto.PostResponseEventDto;

public interface EventService {
    PostResponseEventDto createEventService(PostRequestEventDto dto);
}
