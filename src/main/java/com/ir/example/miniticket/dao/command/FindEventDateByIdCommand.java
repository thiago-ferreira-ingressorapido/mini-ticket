package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.EventDateRowMapper;
import com.ir.example.miniticket.model.EventDate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Function;

public class FindEventDateByIdCommand implements Function<UUID, EventDate> {

    private static final String QUERY = "SELECT * " +
                                        "FROM events_date " +
                                        "WHERE ID = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindEventDateByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public EventDate apply(UUID uuid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", uuid);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, new EventDateRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
