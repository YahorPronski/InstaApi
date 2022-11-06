package com.company.instaapi.service;

import com.company.instaapi.domain.user.User;
import com.company.instaapi.domain.user.UserAuthInfo;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByAuthInfo(UserAuthInfo userAuthInfo);
}
