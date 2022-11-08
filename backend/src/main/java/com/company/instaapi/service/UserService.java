package com.company.instaapi.service;

import com.company.instaapi.domain.user.User;
import com.company.instaapi.domain.user.UserAuthInfo;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserService {
    User saveUser(@NotNull User user);
    Optional<User> findUserById(@NotNull String userId);
    Optional<User> findUserByAuthInfo(@NotNull UserAuthInfo userAuthInfo);
}
