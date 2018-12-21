package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.dao.command.CancelTicketCommand;
import com.ir.example.miniticket.dao.command.CreateTicketCommand;
import com.ir.example.miniticket.dao.command.FindAllTicketsCommand;
import com.ir.example.miniticket.dao.command.FindTicketByIdCommand;
import com.ir.example.miniticket.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class TicketDaoImpl implements TicketDao {

    private JdbcTemplate jdbcTemplate;

    public TicketDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ticket> findTicketById(UUID id) {
        return Optional.ofNullable(new FindTicketByIdCommand(jdbcTemplate).apply(id));
    }

    @Override
    public List<Ticket> findAllTickets() {
        return new FindAllTicketsCommand(jdbcTemplate).get();
    }

    @Override
    public void createTicket(Ticket ticket) {
        new CreateTicketCommand(jdbcTemplate).accept(ticket);
    }

    @Override
    public void cancelTicket(UUID id) {
        new CancelTicketCommand(jdbcTemplate).accept(id);
    }
}
