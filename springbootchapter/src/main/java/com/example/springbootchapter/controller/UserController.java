package com.example.springbootchapter.controller;

import com.example.springbootchapter.model.User;
import com.example.springbootchapter.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        logger.info("Fetches all users");
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid User user) {
        logger.info("Adding user: {}", user);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @NotNull Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid User userDetails) {

        logger.info("Updating user with id {}: {}", id, userDetails);
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable @NotNull Long id) {
        logger.info("Fetching user with id: {}", id);
        return userService.getUserById(id);
    }

    @GetMapping("/search/username/{username}")
    public User getUserByUsername(@PathVariable @NotNull String username) {
        logger.info("Fetching user with username: {}", username);
        return userService.findUserByUsername(username);
    }

    @GetMapping("/search/email/{email}")
    public User getUserByEmail(@PathVariable @NotNull String email) {
        logger.info("Fetching user with email: {}", email);
        return userService.findUserByEmail(email);
    }

    @GetMapping("/count")
    public int countUsers() {
        logger.info("Counting all users");
        Integer userCount = userService.countUsers();
        return userCount;
    }
}
