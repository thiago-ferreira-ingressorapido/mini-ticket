package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a presentation date of an <code>Event</code>
 */
@Value.Immutable
@JsonSerialize(as = ImmutableEventDate.class)
@JsonDeserialize(as = ImmutableEventDate.class)
public interface EventDate {

    Optional<UUID> id();

    Optional<UUID> eventId();

    Optional<LocalDateTime> eventDateTime();

    Optional<Integer> availableTickets();

}
