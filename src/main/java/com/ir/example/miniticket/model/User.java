package com.ir.example.miniticket.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * User entity
 * @author thiago-ferreira
 */
@Value.Immutable
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public interface User {

    @Nullable
    UUID id();

    String name();

    String cpf();

    String mobilePhone();

    String email();

    String password();

    LocalDate birthdayDate();

    Gender gender();

    String address();


}
