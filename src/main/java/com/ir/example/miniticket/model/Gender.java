package com.ir.example.miniticket.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender implements EnumType {

    MALE(0),
    FEMALE(1),
    UNDEFINED(2);

    private Integer id;

    Gender(Integer id) {
        this.id = id;
    }

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static Gender forValue(int id) {
        return EnumTypeUtils.getById(Gender.class, id);
    }
}