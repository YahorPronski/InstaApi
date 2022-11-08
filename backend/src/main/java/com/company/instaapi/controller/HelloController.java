package com.company.instaapi.controller;

import com.company.instaapi.domain.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized");
        }

        User authorizedUser = (User) authentication.getPrincipal();
        return String.format("Hello, %s", authorizedUser.getFirstName());
    }
}
