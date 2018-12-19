package com.ir.example.miniticket.controller;

import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.ImmutableMessage;
import com.ir.example.miniticket.model.Message;
import com.ir.example.miniticket.model.User;
import com.ir.example.miniticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller of the <code>User</code> entity.
 * @author thiago-ferreira
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") UUID userId)
        throws ResourceNotFoundException {
        User user = userService.findUserById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable(value = "id") UUID userId)
        throws ResourceNotFoundException {
        User user = userService.findUserById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        userService.deleteUser(user);
        return ResponseEntity.ok().body(
            ImmutableMessage.builder().success(true).message("User deleted successfully").build());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") UUID userId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userService.findUserById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        User updatedUser = this.userService.updateUser(user,userDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
