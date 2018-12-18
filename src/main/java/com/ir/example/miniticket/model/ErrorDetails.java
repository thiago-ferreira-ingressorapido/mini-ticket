package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;

/**
 * Represents an error occurred during a REST request.
 * @author thiago-ferreira
 */
@Value.Immutable
@JsonSerialize(as = ImmutableErrorDetails.class)
@JsonDeserialize(as = ImmutableErrorDetails.class)
public interface ErrorDetails {

    LocalDateTime timestamp();

    String message();

    String details();
}
