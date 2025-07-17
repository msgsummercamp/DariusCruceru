package com.example.springbootchapter.service;

import com.example.springbootchapter.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
}
