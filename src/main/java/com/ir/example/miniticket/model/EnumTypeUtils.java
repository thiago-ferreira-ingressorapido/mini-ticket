package com.ir.example.miniticket.model;

import java.util.Arrays;

public class EnumTypeUtils {

    public static <T extends EnumType> T getById(Class<T> tClass, int id) {

        if (!tClass.isEnum()) {
            throw new IllegalStateException(String.format("Class %s is not an enum!", tClass.getName()));
        }

        return Arrays.asList(tClass.getEnumConstants())
            .stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid id %d for enum %s",
                id,
                tClass.getName())));
    }

}
