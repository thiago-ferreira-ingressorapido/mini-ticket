package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a ticket of an <code>Event</code>
 */
@Value.Immutable
@JsonSerialize(as = ImmutableTicket.class)
@JsonDeserialize(as = ImmutableTicket.class)
public interface Ticket {

    Optional<UUID> id();

    Optional<UUID> eventDateId();

    Optional<UUID> eventId();

    Optional<UUID> userId();

    Optional<Double> totalPrice();

}
