package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.EventDateRowMapper;
import com.ir.example.miniticket.model.EventDate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class FindEventDateByEventIdCommand implements Function<UUID, List<EventDate>> {

    private static final String QUERY = "SELECT * FROM events_date where event_id = :event_id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindEventDateByEventIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<EventDate> apply(UUID uuid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("event_id", uuid);
        try {
            return jdbcTemplate.query(QUERY, parameters, new EventDateRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
