package com.company.authenticationserver.controller;

import com.company.authenticationserver.dto.LoginRequest;
import com.company.authenticationserver.dto.LoginResponse;
import com.company.authenticationserver.dto.RegisterRequest;
import com.company.authenticationserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
    }

    @GetMapping("/validateToken")
    public Long validateToken(@RequestParam String token) {
        Long userId = authService.getUserIdFromToken(token);
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
        return userId;
    }

    @GetMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestParam String token) {
        LoginResponse response = authService.refreshToken(token);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
        return response;
    }

}
