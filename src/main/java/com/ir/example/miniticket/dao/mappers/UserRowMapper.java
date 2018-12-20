package com.ir.example.miniticket.dao.mappers;

import com.ir.example.miniticket.model.Gender;
import com.ir.example.miniticket.model.ImmutableUser;
import com.ir.example.miniticket.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Row mapper of the <code>User</code> entity
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ImmutableUser.builder().id(UUID.fromString(rs.getString("id")))
            .address(rs.getString("address"))
            .birthdayDate(rs.getDate("birthday_date").toLocalDate())
            .cpf(rs.getString("cpf"))
            .email(rs.getString("email"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .mobilePhone(rs.getString("mobile_phone"))
            .gender(Gender.forValue(rs.getInt("gender"))).build();
    }
}
