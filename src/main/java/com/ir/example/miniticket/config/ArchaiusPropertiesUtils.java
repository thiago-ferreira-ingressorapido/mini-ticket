package com.ir.example.miniticket.config;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.Property;
import com.netflix.config.PropertyWrapper;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

/**
 * Utility class used to retrieve data of configuration properties.
 */
public class ArchaiusPropertiesUtils {


    public static Property<Long> newLongProperty(String name, Long defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getLongProperty(name, defaultValue);
    }

    public static Property<Integer> newIntProperty(String name, Integer defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getIntProperty(name, defaultValue);
    }

    public static Property<Boolean> newBooleanProperty(String name, Boolean defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getBooleanProperty(name, defaultValue);
    }

    public static Property<String> newStringProperty(String name, String defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getStringProperty(name, defaultValue);
    }

    public static Property<Float> newFloatProperty(String name, Float defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getFloatProperty(name, defaultValue);
    }

    public static Property<Double> newDoubleProperty(String name, Double defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getDoubleProperty(name, defaultValue);
    }

    public static <T> Property<T> newContextualProperty(String name, T defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getContextualProperty(name, defaultValue);
    }

    public static Property<Optional<String>> newStringProperty(String name) {
        return new PropertyWrapper<Optional<String>>(name, null) {
            @Override
            public Optional<String> getValue() {
                DynamicStringProperty property =
                    DynamicPropertyFactory.getInstance().getStringProperty(getName(), null);

                return Optional.ofNullable(property.get());
            }
        };
    }

}
