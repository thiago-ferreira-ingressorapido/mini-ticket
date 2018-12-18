package com.ir.example.miniticket.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

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

    /**
     * Gets a Gender given it's id.
     *
     * @param id the Gender id.
     * @return the Gender.
     */
    @JsonCreator
    public static Gender valueOf(Integer id) {
        if (id == null) {
            return null;
        }
        int rawId = id;
        for (Gender gender : values()) {
            if (gender.id == rawId) {
                return gender;
            }
        }
        return null;
    }
}
