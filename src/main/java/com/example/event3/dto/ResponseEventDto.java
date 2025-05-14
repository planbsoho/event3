package com.example.event3.dto;

import com.example.event3.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseEventDto {
    //필드
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createDate; //서비스레이어에서 생성시점에 초기화
    private LocalDateTime modifyDate; //서비스레이어에서 수정시점에 초기화

    public ResponseEventDto( Event event ) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.name = event.getName();
        this.createDate = event.getCreateDate();
        this.modifyDate = event.getModifyDate();
    }
    public ResponseEventDto( long id, String title, String content ) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
