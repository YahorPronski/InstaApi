package com.company.authserver.service;

import com.company.authserver.dto.LoginRequest;
import com.company.authserver.dto.LoginResponse;
import com.company.authserver.dto.RegisterRequest;

public interface AuthService {
    LoginResponse loginUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
    Long getUserIdFromToken(String token);
    LoginResponse refreshToken(String token);
}
