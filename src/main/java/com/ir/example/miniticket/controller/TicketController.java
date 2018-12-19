package com.ir.example.miniticket.controller;


import com.ir.example.miniticket.exceptions.InvalidTicketException;
import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.ImmutableMessage;
import com.ir.example.miniticket.model.Message;
import com.ir.example.miniticket.model.Ticket;
import com.ir.example.miniticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller of the Ticket entity.
 * @author thiago-ferreira
 */
@RestController
@RequestMapping("/api/v1")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @GetMapping("/ticket")
    public List<Ticket> findAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "id") UUID ticketId)
        throws ResourceNotFoundException {
        Ticket ticket = ticketService.findTicketById(ticketId)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + ticketId));
        return ResponseEntity.ok().body(ticket);
    }

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) throws ResourceNotFoundException, InvalidTicketException {
        Ticket newTicket = ticketService.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTicket);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Message> deleteTicket(@PathVariable(value = "id") UUID ticketId)
        throws ResourceNotFoundException {

        Ticket ticket = ticketService.findTicketById(ticketId)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + ticketId));

        ticketService.cancelTicket(ticket);

        return ResponseEntity.ok().body(ImmutableMessage.builder().
            success(true).message("Ticket canceled successfully").build());
    }


}
