package com.example.restapi.service;

import com.example.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("ID must be null when creating a new user");
        }
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
