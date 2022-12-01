package com.company.userservice.service.impl;

import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        if (!NumberUtils.isParsable(userId)) {
            return Optional.empty();
        }
        return userRepository.findById(Long.parseLong(userId));
    }

    @Override
    public Optional<User> getUserByCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
