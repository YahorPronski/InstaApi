package com.company.authenticationserver.service;

import com.company.authenticationserver.dto.LoginRequest;
import com.company.authenticationserver.dto.LoginResponse;
import com.company.authenticationserver.dto.RegisterRequest;

public interface AuthenticationService {
    LoginResponse loginUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
    Long getUserIdFromToken(String token);
    LoginResponse refreshToken(String token);
}
