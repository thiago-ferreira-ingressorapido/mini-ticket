package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.function.Consumer;

public class InsertEventCommand implements Consumer<Event> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String INSERT = "insert into events (id,name,description,event_address,price,quantity_tickets) " +
                            "values (:id,:name,:description,:event_address,:price,:quantity_tickets)";

    public InsertEventCommand(JdbcTemplate jdbcTemplate) {
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
        jdbcTemplate.update(INSERT, parameters);

    }
}
