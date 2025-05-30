package com.example.event3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Event {
    //필드
    //- [ ]  `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
    //- [ ]  `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    //- [ ]  각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
//    @Setter
    private Long id;
    private String title;
    private String content;
    private String name;
    private Long pw;
    private LocalDateTime createDate; //서비스레이어에서 생성시점에 초기화
    private LocalDateTime modifyDate; //서비스레이어에서 수정시점에 초기화
    //생성자
    public Event(String title, String content, String name, Long pw){

        this.title = title;
        this.content = content;
        this.name = name;
        this.pw = pw;
    }

    public Event(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    //메서드
    public void setCreateAndModifyDate(LocalDateTime time) {
        this.createDate = time;
        this.modifyDate = time;
    }
    //이름과 할일수정
    public void updateEvent ( String title, String thingsToDo){
        this.title = title;
        this.content = thingsToDo;
    }
    //할일만 수정
    public void updateThingsToDo( String thingsToDo){
        this.content = thingsToDo;
    }

}
