package com.example.event3.repository;

import com.example.event3.dto.PostResponseEventDto;
import com.example.event3.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class jdbcTemplateEventRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public jdbcTemplateEventRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PostResponseEventDto saveEvent(Event event) {

        //simplejdbcinsert = jdbc쿼리를 문자열로작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("event").usingGeneratedKeyColumns("id");
        //event테이블에 인서트하겠다.  이테이블의 키값은 id이다

        Map<String, Object> parameter = new TreeMap<>();
        parameter.put("title", event.getTitle());
        parameter.put("content", event.getContent());
//        parameter.put("title", event.getTitle());
//        parameter.put("thingsToDo", event.getThingsToDo());
//        parameter.put("name", event.getName());
//        parameter.put("create_date", event.getCreateDate());
//        parameter.put("modify_date", event.getModifyDate());

        //저장후 auto로 생성된 키값을 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
        return new PostResponseEventDto(key.longValue(), event.getTitle(), event.getContent());
    }

    @Override
    public List<PostResponseEventDto> findAllEvents() {
        return List.of();
    }

    @Override
    public Event findEventById(Long id) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) {

    }
}
