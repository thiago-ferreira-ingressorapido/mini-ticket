package com.ir.example.miniticket.dao.command;

import com.ir.example.miniticket.model.Gender;
import com.ir.example.miniticket.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.util.function.Consumer;

public class UpdateUserCommand implements Consumer<User> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private String UPDATE = "update users set name = :name,cpf = :cpf, mobile_phone = :mobile_phone ," +
                            "email = :email ,password = :password ,birthday_date = :birthday_date ," +
                            "gender = :gender ,address = :address " +
                            "where id = :id";

    public UpdateUserCommand(JdbcTemplate jdbcTemplate) {
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
        parameters.addValue("birthday_date", Date.valueOf(user.birthdayDate().orElse(null)));
        parameters.addValue("gender", user.gender().orElse(Gender.UNDEFINED));
        parameters.addValue("address", user.address());
        jdbcTemplate.update(UPDATE, parameters);
    }
}
