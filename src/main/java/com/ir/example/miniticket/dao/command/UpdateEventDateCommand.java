package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.EventDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.util.function.Consumer;

public class UpdateEventDateCommand implements Consumer<EventDate> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String UPDATE = "update events_date set event_date_time = :event_date_time , available_tickets = :available_tickets " +
                            "where event_id = :event_id";

    public UpdateEventDateCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(EventDate eventDate) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("event_id", eventDate.eventId().get());
        parameters.addValue("event_date_time", Timestamp.valueOf(eventDate.eventDateTime().orElse(null)));
        parameters.addValue("available_tickets", eventDate.availableTickets().get());
        jdbcTemplate.update(UPDATE, parameters);

    }
}
