package com.example.event3.dto;

import com.example.event3.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class PostResponseEventDto {
    //필드
    private Long id;
    private String title;
    private String thingsToDo;
    private String name;
    private LocalDateTime createDate; //서비스레이어에서 생성시점에 초기화
    private LocalDateTime modifyDate; //서비스레이어에서 수정시점에 초기화

    public PostResponseEventDto(Event event){
        this.id = event.getId();
        this.title = event.getTitle();
        this.thingsToDo = event.getThingsToDo();
        this.name = event.getName();
        this.createDate = event.getCreateDate();
        this.modifyDate = event.getModifyDate();
    }
}
