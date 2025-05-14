package com.example.event3.repository;

import com.example.event3.dto.ResponseEventDto;
import com.example.event3.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Repository
public class jdbcTemplateEventRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public jdbcTemplateEventRepository( DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ResponseEventDto saveEvent( Event event ) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("event").usingGeneratedKeyColumns("id");


        Map<String, Object> parameter = new TreeMap<>();
        parameter.put("title", event.getTitle());
        parameter.put("content", event.getContent());
        parameter.put("name", event.getName());
        parameter.put("pw", event.getPw());

        Number key = jdbcInsert.executeAndReturnKey( new MapSqlParameterSource( parameter ));
        return new ResponseEventDto( key.longValue(), event.getTitle(), event.getContent() );
    }

    @Override
    public List<ResponseEventDto> findAllEvents() {
        return jdbcTemplate.query("select * from event", eventRowMapper());
    }

    @Override
    public Optional<Event> findEventById( Long id ) {
        List<Event> result = jdbcTemplate.query("select * from event where id = ?", eventRowMapperV2(), id);
        return result.stream().findAny();
    }

    private RowMapper<ResponseEventDto> eventRowMapper() {
        return new RowMapper<ResponseEventDto>() {
            @Override
            public ResponseEventDto mapRow( ResultSet rs, int rowNum ) throws SQLException {
                return new ResponseEventDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        };
    }

    private RowMapper<Event> eventRowMapperV2() {
        return new RowMapper<Event>() {
            @Override
            public Event mapRow( ResultSet rs, int rowNum ) throws SQLException {
                return new Event(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        };
    }

    @Override
    public int update( Long id, String title, String content ) {
        return jdbcTemplate.update("update event set title = ?, content = ? where id = ?", title, content, id);
    }

    @Override
    public int updateTitle( Long id, String title ) {
        return jdbcTemplate.update("update event set title = ? where id = ?", title, id);
    }

    @Override
    public int deleteEvent( long id ) {
        return jdbcTemplate.update("delete from event where id = ?", id );
    }

    //반복되는 null검증을 메서드화 repostiory에서 객체검증. 은
    @Override
    public Event findEventByIdOrElseThrow( Long id ) {
        List<Event> result = jdbcTemplate.query("select * from event by id = ?", eventRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND," Does not exist id = " + id));
    }
}
