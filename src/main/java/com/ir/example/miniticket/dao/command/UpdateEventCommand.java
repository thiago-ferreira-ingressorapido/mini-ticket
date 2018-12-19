package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.function.Consumer;

public class UpdateEventCommand implements Consumer<Event> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String UPDATE = "update events set name = :name ,description = :description,event_address = :event_address ,price = :price ,quantity_tickets = :quantity_tickets " +
                            "where id = :id";

    public UpdateEventCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(Event event) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", event.id().get());
        parameters.addValue("name", event.name().orElse(null));
        parameters.addValue("description",event.description().orElse(null));
        parameters.addValue("event_address", event.eventAddress().orElse(null));
        parameters.addValue("price",event.price().orElse(null));
        parameters.addValue("quantity_tickets", event.quantityTickets().orElse(null));
        jdbcTemplate.update(UPDATE, parameters);

    }
}
