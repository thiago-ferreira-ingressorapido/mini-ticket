package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.dao.command.DeleteEventCommand;
import com.ir.example.miniticket.dao.command.FindAllEventsCommand;
import com.ir.example.miniticket.dao.command.FindEventByIdCommand;
import com.ir.example.miniticket.dao.command.InsertEventCommand;
import com.ir.example.miniticket.dao.command.UpdateEventCommand;
import com.ir.example.miniticket.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventDaoImpl implements EventDao {

    private JdbcTemplate jdbcTemplate;

    public EventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> findAllEvents() {
        return new FindAllEventsCommand(jdbcTemplate).get();
    }

    @Override
    public Optional<Event> findEventById(UUID id) {
        return  Optional.ofNullable(new FindEventByIdCommand(jdbcTemplate).apply(id));
    }

    @Override
    public void saveEvent(Event event) {
        new InsertEventCommand(jdbcTemplate).accept(event);
    }

    @Override
    public void updateEvent(Event event) {
        new UpdateEventCommand(jdbcTemplate).accept(event);
    }

    @Override
    public void deleteEvent(UUID id) {
        new DeleteEventCommand(jdbcTemplate).accept(id);
    }
}
