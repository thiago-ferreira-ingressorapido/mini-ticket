package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.dao.command.DeleteEventDateCommand;
import com.ir.example.miniticket.dao.command.FindEventDateByEventIdCommand;
import com.ir.example.miniticket.dao.command.FindEventDateByIdCommand;
import com.ir.example.miniticket.dao.command.InsertEventDateCommand;
import com.ir.example.miniticket.dao.command.UpdateEventDateCommand;
import com.ir.example.miniticket.model.EventDate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

public class EventDateDaoImpl implements EventDateDao {

    private JdbcTemplate jdbcTemplate;

    public EventDateDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EventDate> findEventDatesByEventId(UUID eventId) {
        return new FindEventDateByEventIdCommand(jdbcTemplate).apply(eventId);
    }

    @Override
    public void deleteEventDate(UUID eventId) {
         new DeleteEventDateCommand(jdbcTemplate).accept(eventId);
    }

    @Override
    public void saveEventDate(EventDate eventDate) {
        new InsertEventDateCommand(jdbcTemplate).accept(eventDate);
    }

    @Override
    public void updateEventDate(EventDate eventDate) {
        new UpdateEventDateCommand(jdbcTemplate).accept(eventDate);
    }

    @Override
    public EventDate findEventDateById(UUID id) {
        return new FindEventDateByIdCommand(jdbcTemplate).apply(id);
    }
}
