package com.ir.example.miniticket.service;

import com.ir.example.miniticket.exceptions.InvalidTicketException;
import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketService {

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
     * Creates a new ticket
     * @param ticket
     * @return
     */
    public Ticket createTicket(Ticket ticket) throws ResourceNotFoundException, InvalidTicketException;


    /**
     * Cancel a ticket
     * @param ticket
     */
    public void cancelTicket(Ticket ticket);
}
