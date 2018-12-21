package com.ir.example.miniticket.dao;

import com.ir.example.miniticket.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface with the DAO operations of the User entity.
 * @author thiago-ferreira
 */
public interface UserDao {

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
     */
    public void saveUser(User user);

    /**
     * Updates an existing user
     * @param user User to update
     */
    public void updateUser(User user);

    /**
     * Deletes an user
     * @param id ID of User to delete.
     */
    public void deleteUser(UUID id);


}
