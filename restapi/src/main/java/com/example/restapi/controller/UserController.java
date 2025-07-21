package com.example.restapi.controller;

import com.example.restapi.model.User;
import com.example.restapi.service.IUserService;
import com.example.restapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "User API", description = "Operations related to users")
@RequestMapping("/api/")
@RestController
public class UserController {
    @Autowired
    private final IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Retrieves a paginated list of users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> pagedResult = userService.getAllUsers(pageable);

        return ResponseEntity.ok(pagedResult.getContent());
    }

    @Operation(summary = "Creates a new user")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        logger.info("Adding user: {}", user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Updates an existing user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid User userDetails) {

        logger.info("Updating user with id {}: {}", id, userDetails);
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletes a user by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @NotNull Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Partially updates a user by ID")
    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updatePartialUser(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        User updatedUser = userService.updatePartialUser(id, updates);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/api/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

}
