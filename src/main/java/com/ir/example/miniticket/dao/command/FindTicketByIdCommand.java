package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.TicketRowMapper;
import com.ir.example.miniticket.model.Ticket;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Function;

public class FindTicketByIdCommand implements Function<UUID, Ticket> {

    private static final String QUERY = "SELECT * " +
                                        "FROM ticket " +
                                        "WHERE ID = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindTicketByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Ticket apply(UUID uuid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", uuid);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, new TicketRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
