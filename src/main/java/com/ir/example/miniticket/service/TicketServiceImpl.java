package com.ir.example.miniticket.service;

import com.ir.example.miniticket.dao.TicketDao;
import com.ir.example.miniticket.exceptions.InvalidTicketException;
import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.Event;
import com.ir.example.miniticket.model.EventDate;
import com.ir.example.miniticket.model.ImmutableEventDate;
import com.ir.example.miniticket.model.ImmutableTicket;
import com.ir.example.miniticket.model.Ticket;
import com.ir.example.miniticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Optional<Ticket> findTicketById(UUID id) {
        return this.ticketDao.findTicketById(id);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketDao.findAllTickets();
    }

    @Override
    public Ticket createTicket(Ticket ticket) throws ResourceNotFoundException, InvalidTicketException {

        //Validates the ticket
        this.validateTicket(ticket);

        //Gets the event its list of event date
        Event event = eventService.findEventById(ticket.eventId().get())
            .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + ticket.eventId()));
        List<EventDate> eventDates = eventService.findEventDatesByEventId(event.id().get());

        //Validates the event
        this.validateEvent(ticket, eventDates);

        //Validates the user
        User user = userService.findUserById(ticket.userId().get()).
            orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + ticket.userId()));

        //Save a new ticket
        Ticket newTicket = ImmutableTicket.copyOf(ticket)
            .withId(UUID.randomUUID())
            .withTotalPrice(event.price())
            .withUserId(user.id());

        this.ticketDao.createTicket(newTicket);

        //Updates the quantity of tickets available for the date.
        EventDate currentEventDate =
            eventDates.stream().filter(eDate -> eDate.id().equals(ticket.eventDateId())).findFirst().get();

        eventService.updateEventDateAvailableTickets(currentEventDate.id().get(),
            currentEventDate.availableTickets().get() - ONE);

        return newTicket;
    }

    /**
     * Cancel a ticket
     **/
    @Override
    public void cancelTicket(Ticket ticket) {

        //Cancels the ticket
        ticketDao.cancelTicket(ticket.id().get());

        //Add one ticket in the event date
        EventDate eventDate = eventService.findEventDateById(ticket.eventDateId().get());

        eventService.updateEventDateAvailableTickets(eventDate.id().get(),
            eventDate.availableTickets().get() - ONE);

    }

    /**
     * Validates if the given ticket is correctly created
     *
     * @param ticket
     * @throws InvalidTicketException
     */
    private void validateTicket(Ticket ticket) throws InvalidTicketException {


        if (!ticket.eventId().isPresent()) {
            throw new InvalidTicketException("Is mandatory the Event ID");
        }

        if (!ticket.eventDateId().isPresent()) {
            throw new InvalidTicketException("Is mandatory the EventDate ID");
        }

        if (!ticket.userId().isPresent()) {
            throw new InvalidTicketException("Is mandatory the User ID");
        }
    }

    /**
     * Validates if the Event has EventDate available, and if them belongs each other.
     * Also validates if there are capacity available
     *
     * @param ticket
     * @param eventDates
     * @throws InvalidTicketException
     */
    private void validateEvent(Ticket ticket, List<EventDate> eventDates) throws InvalidTicketException {
        if (eventDates.isEmpty()) {
            throw new InvalidTicketException("The given Event does not has EventDate available");
        } else {
            EventDate eventDate = eventDates.stream()
                .filter(eDate -> eDate.id().equals(ticket.eventDateId()))
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException("The given EventDate does not belong to the given Event"));
            if (eventDate.availableTickets().isPresent() && eventDate.availableTickets().get() == ZERO) {
                throw new InvalidTicketException("The given Event does not has capacity available");
            }
        }
    }
}
