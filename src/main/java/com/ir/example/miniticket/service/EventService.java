package com.ir.example.miniticket.service;

import com.ir.example.miniticket.model.Event;
import com.ir.example.miniticket.model.EventDate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface with the services of the Event entity
 * @author thiago-ferreira
 */
public interface EventService {

    /**
     * Finds all events
     * @return
     */
    List<Event> findAllEvents();

    /**
     * Finds a event by id
     * @param eventId
     * @return
     */
    Optional<Event> findEventById(UUID eventId);

    /**
     * Delete an event
     * @param event
     */
    void deleteEvent(Event event);

    /**
     * Creates a new event
     * @param event
     * @return
     */
    Event saveEvent(Event event);

    /**
     * Updates an event
     * @param currentEvent Event with current data
     * @param eventDetails Event with the new data
     * @return
     */
    Event updateEvent(Event currentEvent, Event eventDetails);

    /**
     * Finds all Event date of a given event
     * @param eventId
     * @return
     */
    public List<EventDate> findEventDatesByEventId(UUID eventId);

    /**
     * Updates an event date
     * @param eventDate
     */
    public void updateEventDate(EventDate eventDate);

    /**
     * Finds an event date by id
     * @param id
     * @return
     */
    public EventDate findEventDateById(UUID id);


}
