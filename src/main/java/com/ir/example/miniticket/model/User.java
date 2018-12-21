package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * User entity
 */
@Value.Immutable
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public interface User {

    Optional<UUID> id();

    Optional<String> name();

    Optional<String> cpf();

    Optional<String> mobilePhone();

    Optional<String> email();

    Optional<String> password();

    Optional<LocalDate> birthdayDate();

    Optional<Gender> gender();

    Optional<String> address();


}
