package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.model.EventDate;

import java.util.List;
import java.util.UUID;

/**
 * Interface DAO of the entity <code>EventDate</code>
 * @author thiago-ferreira
 */
public interface EventDateDao {

    /**
     * Finds all event dates of a given event id
     * @param eventId
     * @return
     */
    public List<EventDate> findEventDatesByEventId(UUID eventId);

    /**
     * Deletes an event date
     * @param eventId
     */
    public void deleteEventDate(UUID eventId);

    /**
     * Creates a new event date.
     * @param eventDate
     */
    public void saveEventDate(EventDate eventDate);

    /**
     * Updates a given event date
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
