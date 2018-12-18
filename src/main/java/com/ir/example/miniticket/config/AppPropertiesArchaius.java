package com.ir.example.miniticket.config;

import com.netflix.config.Property;

import static com.ir.example.miniticket.config.ArchaiusPropertiesUtils.newStringProperty;


public class AppPropertiesArchaius implements  AppProperties {

    @Override
    public Property<String> jdbcDriverClassName() {
        return  newStringProperty("jdbc.driverClassName", "org.postgresql.Driver");
    }

    @Override
    public Property<String> jdbcUrl() {
        return  newStringProperty("jdbc.url", "jdbc:postgresql://localhost:5432/mini-ticket");
    }

    @Override
    public Property<String> jdbcUsername() {
        return  newStringProperty("jdbc.username", "postgres");
    }

    @Override
    public Property<String> jdbcPassword() {
        return  newStringProperty("jdbc.password", "postgres");
    }

    @Override
    public Property<String> jdbcConnectionTestQuery() {
        return  newStringProperty("jdbc.testQuery", "SELECT 1");
    }
}
