package com.example.restapi.service;

import com.example.restapi.model.Role;
import com.example.restapi.model.User;
import com.example.restapi.repository.RoleRepository;
import com.example.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(new HashSet<>(Set.of(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);


        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User updatePartialUser(Long id, Map<String, Object> updates) {
        User user = getUserById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "username" -> user.setUsername((String) value);
                case "email" -> user.setEmail((String) value);
                case "password" -> user.setPassword((String) value);
                case "firstName" -> user.setFirstName((String) value);
                case "lastName" -> user.setLastName((String) value);
            }
        });

        return userRepository.save(user);
    }
}
