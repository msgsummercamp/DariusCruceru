package com.example.springbootchapter.repository;

import com.example.springbootchapter.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    void save(User user);
    void deleteById(Long id);
    void update(User user);
    User findById(Long id);
}
