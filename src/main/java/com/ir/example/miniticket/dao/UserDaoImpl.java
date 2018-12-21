package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.dao.command.DeleteUserCommand;
import com.ir.example.miniticket.dao.command.FindAllUsersCommand;
import com.ir.example.miniticket.dao.command.FindUserByIdCommand;
import com.ir.example.miniticket.dao.command.InsertUserCommand;
import com.ir.example.miniticket.dao.command.UpdateUserCommand;
import com.ir.example.miniticket.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        return new FindAllUsersCommand(jdbcTemplate).get();
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return Optional.ofNullable(new FindUserByIdCommand(jdbcTemplate).apply(id));
    }

    @Override
    public void saveUser(User user) {
        new InsertUserCommand(jdbcTemplate).accept(user);
    }

    @Override
    public void updateUser(User user) {
        new UpdateUserCommand(jdbcTemplate).accept(user);
    }

    @Override
    public void deleteUser(UUID id) {
        new DeleteUserCommand(jdbcTemplate).accept(id);
    }
}
