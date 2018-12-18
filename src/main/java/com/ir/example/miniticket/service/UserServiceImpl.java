package com.ir.example.miniticket.service;

import com.ir.example.miniticket.dao.UserDao;
import com.ir.example.miniticket.model.ImmutableUser;
import com.ir.example.miniticket.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return userDao.findUserById(id);
    }

    @Override
    public User saveUser(User user) {
        UUID id = UUID.randomUUID();
        User newUser = this.buildUser(user,id);
        userDao.saveUser(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User oldUser, User newUser) {
        User updatedUser = this.buildUser(newUser,oldUser.id());
        userDao.updateUser(updatedUser);
        return updatedUser;

    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user.id());
    }

    private User buildUser(User user, UUID id){
        return ImmutableUser.builder().id(id)
            .address(user.address())
            .birthdayDate(user.birthdayDate())
            .cpf(user.cpf())
            .email(user.email())
            .name(user.name())
            .password(user.password())
            .mobilePhone(user.mobilePhone())
            .gender(user.gender()).build();
    }
}
