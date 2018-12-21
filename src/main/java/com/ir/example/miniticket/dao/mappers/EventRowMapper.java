package com.ir.example.miniticket.dao.mappers;

import com.ir.example.miniticket.model.Event;
import com.ir.example.miniticket.model.ImmutableEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ImmutableEvent.builder().
            description(Optional.ofNullable(rs.getString("description"))).
            eventAddress(Optional.ofNullable(rs.getString("event_address"))).
            id(UUID.fromString(rs.getString("id"))).
            name(Optional.ofNullable(rs.getString("name"))).
            price(Optional.ofNullable(rs.getDouble("price"))).
            quantityTickets(Optional.ofNullable(rs.getInt("quantity_tickets"))).build();

    }
}
