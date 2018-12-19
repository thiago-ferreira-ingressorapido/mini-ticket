package com.ir.example.miniticket.controller;

import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.Event;
import com.ir.example.miniticket.model.ImmutableEvent;
import com.ir.example.miniticket.model.ImmutableMessage;
import com.ir.example.miniticket.model.Message;
import com.ir.example.miniticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller of the <code>User</code> entity.
 * @author thiago-ferreira
 */
@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable(value = "id") UUID eventId)
        throws ResourceNotFoundException {
        Event event = eventService.findEventById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));
        return ResponseEntity.ok().body(event);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Message> deleteEvent(@PathVariable(value = "id") UUID eventId)
        throws ResourceNotFoundException {

        Event event = eventService.findEventById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

        eventService.deleteEvent(event);
        return ResponseEntity.ok().body(
            ImmutableMessage.builder().success(true).message("Event deleted successfully").build());
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event newEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }


    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") UUID eventId,
                                           @Valid @RequestBody Event eventDetails) throws ResourceNotFoundException {

        Event currentEvent = eventService.findEventById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

        Event updatedEvent = this.eventService.updateEvent(currentEvent,eventDetails);
        return ResponseEntity.ok(updatedEvent);
    }

}
