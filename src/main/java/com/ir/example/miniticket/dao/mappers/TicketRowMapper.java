package com.ir.example.miniticket.dao.mappers;

import com.ir.example.miniticket.model.ImmutableTicket;
import com.ir.example.miniticket.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * RowMapper of Ticket
 */
public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ImmutableTicket.builder().id(UUID.fromString(rs.getString("id")))
            .eventDateId(UUID.fromString(rs.getString("event_date_id")))
            .eventId(UUID.fromString(rs.getString("event_id")))
            .userId(UUID.fromString(rs.getString("user_id")))
            .totalPrice(rs.getDouble("total_price")).build();
    }
}
