package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.EventRowMapper;
import com.ir.example.miniticket.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class FindAllEventsCommand implements Supplier<List<Event>> {

    private static final String QUERY = "SELECT * FROM events";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindAllEventsCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Event> get() {
        try {
            return jdbcTemplate.query(QUERY,new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
