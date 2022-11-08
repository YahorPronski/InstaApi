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

    @Override
    public Optional<User> findUserById(@NotNull Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserById(@NotNull String userId) {
        try {
            return userRepository.findById(Long.parseLong(userId));
        } catch (NumberFormatException e) {
            System.out.printf("Cannot parse userId = %s into long", userId);
        }
        return Optional.empty();
    }

    public Optional<User> findUserByAuthInfo(@NotNull UserAuthInfo userAuthInfo) {
        Optional<User> userOpt = userRepository.findById(userAuthInfo.getId());
        if (userOpt.isPresent() && passwordEncoder.matches(userAuthInfo.getPassword(), userOpt.get().getAuthInfo().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}
