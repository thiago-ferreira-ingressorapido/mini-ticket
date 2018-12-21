package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface with the DAO operations of the entity <code>Event</code>
 * @author thiago-ferreira
 */
public interface EventDao {

    /**
     * Searches all persisted events.
     * @return
     */
    public List<Event> findAllEvents();

    /**
     * Retrieves an event by its id.
     * @param id Event id
     * @return
     */
    public Optional<Event> findEventById(UUID id);

    /**
     * Save a new event
     * @param event Event to persist.
     */
    public void saveEvent(Event event);

    /**
     * Updates an existing event
     * @param event Event to update
     */
    public void updateEvent(Event event);

    /**
     * Deletes an event
     * @param id ID of the event to delete.
     */
    public void deleteEvent(UUID id);

}
