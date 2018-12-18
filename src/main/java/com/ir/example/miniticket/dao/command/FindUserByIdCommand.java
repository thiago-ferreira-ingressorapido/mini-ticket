package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.UserRowMapper;
import com.ir.example.miniticket.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;
import java.util.function.Function;

public class FindUserByIdCommand implements Function<UUID, User> {

    private static final String QUERY = "SELECT * " +
                                        "FROM users " +
                                        "WHERE ID = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindUserByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public User apply(UUID uuid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", uuid);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
