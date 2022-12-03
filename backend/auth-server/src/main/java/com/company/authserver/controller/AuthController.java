package com.company.authserver.controller;

import com.company.authserver.dto.LoginRequest;
import com.company.authserver.dto.LoginResponse;
import com.company.authserver.dto.RegisterRequest;
import com.company.authserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
        return authService.getUserIdFromToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token"));
    }

    @GetMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestParam String token) {
        return authService.refreshToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token"));
    }

}
