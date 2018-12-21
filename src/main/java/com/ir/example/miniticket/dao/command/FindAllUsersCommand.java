package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.dao.mappers.UserRowMapper;
import com.ir.example.miniticket.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class FindAllUsersCommand implements Supplier<List<User>> {

    private static final String QUERY = "SELECT * FROM users";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindAllUsersCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<User> get() {
       try {
            return jdbcTemplate.query(QUERY,new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
