package com.company.authserver.service;

import com.company.authserver.dto.LoginRequest;
import com.company.authserver.dto.LoginResponse;
import com.company.authserver.dto.RegisterRequest;

import java.util.Optional;

public interface AuthService {
    LoginResponse loginUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
    Optional<Long> getUserIdFromToken(String token);
    Optional<LoginResponse> refreshToken(String token);
}
