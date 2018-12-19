package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents an event (concert,presentation,etc)
 */
@Value.Immutable
@JsonSerialize(as = ImmutableEvent.class)
@JsonDeserialize(as = ImmutableEvent.class)
public interface Event {

    Optional<UUID> id();

    Optional<String> name();

    Optional<String> description();

    Optional<String> eventAddress();

    Optional<Double> price();

    Optional<Integer> quantityTickets();

    Optional<List<EventDate>> eventDates();


}
