package com.ir.example.miniticket.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Event {

    String name();

    String description();

}
