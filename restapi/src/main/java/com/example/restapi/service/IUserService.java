package com.example.restapi.service;

import com.example.restapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IUserService {
    User createUser(User user);

    User getUserById(Long id);

    Page<User> getAllUsers(Pageable pageable);

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);

    User updatePartialUser(Long id, Map<String, Object> updates);

}
