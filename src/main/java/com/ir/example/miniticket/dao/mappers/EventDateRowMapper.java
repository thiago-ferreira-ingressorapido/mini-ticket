package com.ir.example.miniticket.dao.mappers;

import com.ir.example.miniticket.model.EventDate;
import com.ir.example.miniticket.model.ImmutableEventDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class EventDateRowMapper implements RowMapper<EventDate> {

    @Override
    public EventDate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ImmutableEventDate.builder().availableTickets(Optional.ofNullable(rs.getInt("available_tickets")))
            .eventDateTime(Optional.ofNullable(rs.getTimestamp("event_date_time").toLocalDateTime()))
            .eventId(Optional.ofNullable(UUID.fromString(rs.getString("event_id"))))
            .id(Optional.ofNullable(UUID.fromString(rs.getString("id")))).build();
    }
}
