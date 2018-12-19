package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.Gender;
import com.ir.example.miniticket.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.util.function.Consumer;

public class InsertUserCommand implements Consumer<User> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private String INSERT = "insert into users (id,name,cpf,mobile_phone,email,password,birthday_date,gender,address) " +
                            "values (:id,:name,:cpf,:mobile_phone,:email,:password,:birthday_date,:gender,:address)";

    public InsertUserCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", user.id());
        parameters.addValue("name", user.name());
        parameters.addValue("cpf", user.cpf());
        parameters.addValue("mobile_phone", user.mobilePhone());
        parameters.addValue("email", user.email());
        parameters.addValue("password", user.password());
        parameters.addValue("birthday_date", Date.valueOf(user.birthdayDate().get()));
        parameters.addValue("gender", user.gender().orElse(Gender.UNDEFINED));
        parameters.addValue("address", user.address());
        jdbcTemplate.update(INSERT, parameters);

    }
}
