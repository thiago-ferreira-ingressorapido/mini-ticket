package com.ir.example.miniticket.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Consumer;

public class DeleteEventDateCommand implements Consumer<UUID> {

    private static final String QUERY = "DELETE " +
                                        "FROM events_date " +
                                        "WHERE event_id = :event_id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public DeleteEventDateCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(UUID id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("event_id", id);
        jdbcTemplate.update(QUERY, parameters);
    }
}
