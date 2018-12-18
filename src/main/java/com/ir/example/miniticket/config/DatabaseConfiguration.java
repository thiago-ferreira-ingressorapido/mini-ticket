package com.ir.example.miniticket.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Configures the database connection.
 * @author thiago-ferreira
 */
@Component
public class DatabaseConfiguration {

    @Bean
    public HikariDataSource dataSource() {
        AppProperties properties = AppPropertiesProvider.getInstance();
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.jdbcDriverClassName().getValue());
        dataSource.setJdbcUrl(properties.jdbcUrl().getValue());
        dataSource.setUsername(properties.jdbcUsername().getValue());
        dataSource.setPassword(properties.jdbcPassword().getValue());
        dataSource.setConnectionTestQuery(properties.jdbcConnectionTestQuery().getValue());

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
