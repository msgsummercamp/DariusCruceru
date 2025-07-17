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
        users.add(new User(1L, "John Doe"));
        users.add(new User(2L, "Jane Doe"));
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public void update(User user) {
        users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .ifPresent(existingUser -> {
                    users.remove(existingUser);
                    users.add(user);
                });
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public void clear() {
        users.clear();
    }
}
