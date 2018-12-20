package com.ir.example.miniticket.config;

import com.netflix.config.Property;

/**
 * Interface with the properties definitions.
 *
 */
public interface AppProperties {

    /**
     * JDBC Driver Class
     */
    Property<String> jdbcDriverClassName();
    /**
     * JDBC URL
     */
    Property<String> jdbcUrl();

    /**
     * JDBC Username
     */
    Property<String> jdbcUsername();

    /**
     * JDBC Password
     */
    Property<String> jdbcPassword();

    /**
     * JDBC Test query
     */
    Property<String> jdbcConnectionTestQuery();

}
