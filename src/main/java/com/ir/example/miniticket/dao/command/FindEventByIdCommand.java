package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.EventRowMapper;
import com.ir.example.miniticket.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Function;

public class FindEventByIdCommand implements Function<UUID, Event> {

    private static final String QUERY = "SELECT * " +
                                        "FROM events " +
                                        "WHERE ID = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindEventByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Event apply(UUID uuid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", uuid);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
