package com.company.userservice.service;

import com.company.userservice.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserByCredentials(String username, String password);
    boolean isEmailExists(String email);
    boolean isUsernameExists(String username);
}
