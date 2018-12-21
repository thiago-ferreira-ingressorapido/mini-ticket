package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.TicketRowMapper;
import com.ir.example.miniticket.model.Ticket;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class FindAllTicketsCommand implements Supplier<List<Ticket>> {

    private static final String QUERY = "SELECT * FROM ticket";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindAllTicketsCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Ticket> get() {
        try {
            return jdbcTemplate.query(QUERY,new TicketRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
