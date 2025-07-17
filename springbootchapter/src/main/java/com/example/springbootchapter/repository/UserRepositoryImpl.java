package com.example.springbootchapter.repository;

import com.example.springbootchapter.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        users.add(new User(1, "John Doe"));
        users.add(new User(2, "Jane Doe"));
    }

    @Override
    public List<User> findAll() {
       return Collections.unmodifiableList(users);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
