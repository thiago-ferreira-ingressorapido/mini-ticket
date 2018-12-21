package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.function.Consumer;

public class CreateTicketCommand implements Consumer<Ticket> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String INSERT = "insert into ticket (id,event_id,event_date_id,user_id,total_price) " +
                            "values (:id,:event_id,:event_date_id,:user_id,:total_price)";

    public CreateTicketCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", ticket.id().get());
        parameters.addValue("event_id", ticket.eventId().get());
        parameters.addValue("event_date_id", ticket.eventDateId().get());
        parameters.addValue("user_id", ticket.userId().get());
        parameters.addValue("total_price", ticket.totalPrice().get());
        jdbcTemplate.update(INSERT, parameters);
    }
}
