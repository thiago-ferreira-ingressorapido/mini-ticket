package com.ir.example.miniticket.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.BiConsumer;

public class UpdateEventDateTicketsCommand implements BiConsumer<UUID,Integer> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String UPDATE = "update events_date set available_tickets = :available_tickets " +
                            "where id = :id";

    public UpdateEventDateTicketsCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }


    /**
     * Performs this operation on the given arguments.
     *
     * @param uuid    the first input argument
     * @param integer the second input argument
     */
    @Override
    public void accept(UUID uuid, Integer integer) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", uuid);
        parameters.addValue("available_tickets", integer);
        jdbcTemplate.update(UPDATE, parameters);

    }
}
