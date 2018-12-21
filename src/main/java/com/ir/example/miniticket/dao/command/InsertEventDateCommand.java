package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.EventDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.util.function.Consumer;

public class InsertEventDateCommand implements Consumer<EventDate> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String INSERT = "insert into events_date (id,event_id,event_date_time,available_tickets) " +
                            "values (:id,:event_id,:event_date_time,:available_tickets)";

    public InsertEventDateCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(EventDate eventDate) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", eventDate.id().get());
        parameters.addValue("event_id", eventDate.eventId().get());
        parameters.addValue("event_date_time", Timestamp.valueOf(eventDate.eventDateTime().orElse(null)));
        parameters.addValue("available_tickets", eventDate.availableTickets().get());
        jdbcTemplate.update(INSERT, parameters);

    }

}
