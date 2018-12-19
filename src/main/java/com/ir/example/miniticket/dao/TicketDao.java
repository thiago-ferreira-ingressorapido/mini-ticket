package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface DAO for Ticket entity
 */
public interface TicketDao {

    /**
     * Finds a ticket by ID
     * @param id
     * @return
     */
    public Optional<Ticket> findTicketById(UUID id);

    /**
     * Finds all tickets
     * @return
     */
    public List<Ticket> findAllTickets();


    /**
     * Creates a new Ticket
     * @param ticket
     */
    public void createTicket(Ticket ticket);

    /**
     * Cancel a ticket (delete)
     * @param id
     */
    public void cancelTicket(UUID id);

}
