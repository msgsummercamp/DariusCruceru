package com.example.springbootchapter.controller;

import com.example.springbootchapter.model.User;
import com.example.springbootchapter.service.UserService;
import com.example.springbootchapter.service.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public void addUser(@RequestBody @Valid User user) {
        logger.info("Adding user: {}", user);
        userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotNull Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid User user) {
        logger.info("Updating user: {}", user);
        userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable @NotNull Long id) {
        logger.info("Fetching user with id: {}", id);
        return userService.getUserById(id);
    }

}
