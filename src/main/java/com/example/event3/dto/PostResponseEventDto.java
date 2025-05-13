package com.example.event3.dto;

import com.example.event3.entity.Event;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseEventDto {
    //필드
    private Long id;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createDate; //서비스레이어에서 생성시점에 초기화
    private LocalDateTime modifyDate; //서비스레이어에서 수정시점에 초기화

    public PostResponseEventDto(Event event){
        this.id = event.getId();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.name = event.getName();
        this.createDate = event.getCreateDate();
        this.modifyDate = event.getModifyDate();
    }

    public PostResponseEventDto(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
