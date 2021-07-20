package com.example.user.service.repository.impl;

import com.example.user.service.exception.UserNotFoundException;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public User getUser(String email) {
        return list.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        list.add(user);
        return user;
    }

    @Override
    public User updateUser(String email, User user) {
        boolean isDeleted = list.removeIf(u -> u.getEmail().equals(email));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new RuntimeException("User does not exists!");
        }
        return user;
    }

    @Override
    public void deleteUser(String email) {
        list.removeIf(u -> u.getEmail().equals(email));
    }
}
