package com.ir.example.miniticket.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Consumer;

public class CancelTicketCommand implements Consumer<UUID> {

    private static final String QUERY = "DELETE " +
                                        "FROM ticket " +
                                        "WHERE id = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CancelTicketCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(UUID id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        jdbcTemplate.update(QUERY, parameters);
    }

}
