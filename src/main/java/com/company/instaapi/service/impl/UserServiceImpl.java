package com.company.instaapi.service.impl;

import com.company.instaapi.domain.user.User;
import com.company.instaapi.domain.user.UserAuthInfo;
import com.company.instaapi.repository.UserRepository;
import com.company.instaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(@NotNull User user) {
        String encryptedPassword = passwordEncoder.encode(user.getAuthInfo().getPassword());
        user.getAuthInfo().setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(@NotNull String username) {
        return Optional.of(userRepository.findUserByUsername(username));
    }

    public Optional<User> findUserByAuthInfo(@NotNull UserAuthInfo userAuthInfo) {
        User user = userRepository.findUserByUsername(userAuthInfo.getUsername());
        if (user != null && passwordEncoder.matches(userAuthInfo.getPassword(), user.getAuthInfo().getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
