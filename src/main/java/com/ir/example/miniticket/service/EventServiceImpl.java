package com.ir.example.miniticket.service;

import com.ir.example.miniticket.dao.EventDao;
import com.ir.example.miniticket.dao.EventDateDao;
import com.ir.example.miniticket.model.Event;
import com.ir.example.miniticket.model.EventDate;
import com.ir.example.miniticket.model.ImmutableEvent;
import com.ir.example.miniticket.model.ImmutableEventDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    private EventDateDao eventDateDao;

    public EventServiceImpl(EventDao eventDao,EventDateDao eventDateDao ) {
        this.eventDao = eventDao;
        this.eventDateDao = eventDateDao;
    }

    @Override
    public List<Event> findAllEvents() {
        return eventDao.findAllEvents();
    }

    @Override
    public Optional<Event> findEventById(UUID eventId) {
        Optional<Event> event = eventDao.findEventById(eventId);
        if(event.isPresent()){
            List<EventDate> eventDates = eventDateDao.findEventDatesByEventId(eventId);
            if(eventDates != null && !eventDates.isEmpty()){
                return Optional.of(ImmutableEvent.copyOf(event.get()).withEventDates(eventDates));
            }
        }
        return event;
    }

    @Override
    public void deleteEvent(Event event) {
        UUID eventId = event.id().get();
        //Deletes the event dates associated to the given event
        eventDateDao.deleteEventDate(eventId);

        //Then deletes the event
        eventDao.deleteEvent(eventId);
    }

    @Override
    public Event saveEvent(Event event) {
        UUID eventId = this.generateId();

        //Creates the event
        Event newEvent = ImmutableEvent.copyOf(event).withId(eventId);
        eventDao.saveEvent(newEvent);

        //Enriches the EventDate object
        List<EventDate> eventDates = event.eventDates()
            .stream().map((eventDate) -> {
               return ImmutableEventDate.copyOf(eventDate).
                   withId(this.generateId()).
                   withEventId(eventId).
                   withAvailableTickets(event.quantityTickets());
            }).collect(Collectors.toList());

        //Saves each event date then..
        eventDates.stream().forEach((eventDate) -> {
            eventDateDao.saveEventDate(eventDate);
        });

        return ImmutableEvent.copyOf(newEvent).withEventDates(eventDates);
    }

    @Override
    public Event updateEvent(Event currentEvent, Event eventDetails) {

        //Merges the event, with the old and new values

        ImmutableEvent.Builder builder = ImmutableEvent.builder();

        if(eventDetails.description().isPresent()) {
            builder.description(eventDetails.description());
        } else {
            builder.description(currentEvent.description());
        }

        if(eventDetails.eventAddress().isPresent()) {
            builder.eventAddress(eventDetails.eventAddress());
        } else {
            builder.eventAddress(currentEvent.eventAddress());
        }

        if(eventDetails.name().isPresent()) {
            builder.name(eventDetails.name());
        } else {
            builder.name(currentEvent.name());
        }

        if(eventDetails.price().isPresent()) {
            builder.price(eventDetails.price());
        } else {
            builder.price(currentEvent.price());
        }

        if(eventDetails.quantityTickets().isPresent()) {
            builder.quantityTickets(eventDetails.quantityTickets());
        } else {
            builder.quantityTickets(currentEvent.quantityTickets());
        }

        if(eventDetails.id().isPresent()) {
            builder.id(eventDetails.id());
        } else {
            builder.id(currentEvent.id());
        }

        Event updatedEvent = builder.build();
        //Saves the changes
        eventDao.updateEvent(updatedEvent);

        return updatedEvent;
    }

    @Override
    public List<EventDate> findEventDatesByEventId(UUID eventId) {
        return eventDateDao.findEventDatesByEventId(eventId);
    }

    @Override
    public void updateEventDate(EventDate eventDate) {
        eventDateDao.updateEventDate(eventDate);
    }

    /**
     * Updates the quantity of the tickets available
     *
     * @param eventDateId
     * @param quantity
     */
    @Override
    public void updateEventDateAvailableTickets(UUID eventDateId, Integer quantity) {
        eventDateDao.updateEventDateAvailableTickets(eventDateId,quantity);
    }

    @Override
    public EventDate findEventDateById(UUID id) {
        return eventDateDao.findEventDateById(id);
    }

    /**
     * Generates a new UUID for the entities
     * @return
     */
    private UUID generateId(){
        return UUID.randomUUID();
    }
}
