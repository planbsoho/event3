package com.example.event3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Event {
    //필드
    //- [ ]  `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
    //- [ ]  `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    //- [ ]  각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    @Setter
    private Long id;
    private String title;
    private String thingsToDo;
    private String name;
    private Long pw;
    private LocalDateTime createDate; //서비스레이어에서 생성시점에 초기화
    private LocalDateTime modifyDate; //서비스레이어에서 수정시점에 초기화
    //생성자
    public Event(String title, String thingsToDo,String name, Long pw){

        this.title = title;
        this.thingsToDo = thingsToDo;
        this.name = name;
        this.pw = pw;
    }
    //메서드
    public void setCreateAndModifyDate(LocalDateTime time) {
        this.createDate = time;
        this.modifyDate = time;
    }
    public void updateEvent ( String title, String thingsToDo){
        this.title = title;
        this.thingsToDo = thingsToDo;
    }

}
