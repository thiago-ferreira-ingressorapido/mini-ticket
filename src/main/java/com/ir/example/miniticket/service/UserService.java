package com.ir.example.miniticket.service;

import com.ir.example.miniticket.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Interface with business rules of <code>User</code> entity.
 * @author thiago-ferreira
 */
public interface UserService {


    /**
     * Searches all persisted users.
     * @return
     */
    public List<User> findAllUsers();

    /**
     * Retrieves an user by its id.
     * @param id User id
     * @return
     */
    public Optional<User> findUserById(UUID id);

    /**
     * Save a new user
     * @param user User to persist.
     * @return
     */
    public User saveUser(User user);

    /**
     * Updates an existing user
     * @param user User to update
     */
    public User updateUser(User user, User newUser);

    /**
     * Deletes an user
     * @param user User to delete.
     */
    public void deleteUser(User user);

}
