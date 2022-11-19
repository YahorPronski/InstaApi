package com.company.authenticationserver.service.impl;

import com.company.authenticationserver.dto.LoginRequest;
import com.company.authenticationserver.dto.LoginResponse;
import com.company.authenticationserver.dto.RegisterRequest;
import com.company.authenticationserver.service.AuthenticationService;
import com.company.authenticationserver.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final WebClient.Builder webClientBuilder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Long userId = webClientBuilder.build().post()
                .uri("http://user-service/api/users/id")
                .body(BodyInserters.fromValue(loginRequest))
                .retrieve()
                .bodyToMono(Long.class)
                .doOnError(error -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, error.getMessage());
                })
                .block();
        return generateTokens(userId.toString());
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        webClientBuilder.build().post()
                .uri("http://user-service/api/users")
                .body(BodyInserters.fromValue(registerRequest))
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(error -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, error.getMessage());
                })
                .block();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        if (jwtUtil.validateAccessToken(token)) {
            String userId = jwtUtil.getAccessTokenClaims(token).getSubject();
            return Long.parseLong(userId);
        }
        return null;
    }

    @Override
    public LoginResponse refreshToken(String token) {
        if (jwtUtil.validateRefreshToken(token)) {
            String userId = jwtUtil.getRefreshTokenClaims(token).getSubject();
            return generateTokens(userId);
        }
        return null;
    }

    public LoginResponse generateTokens(String userId) {
        return LoginResponse.builder()
                .accessToken(jwtUtil.generateAccessToken(userId))
                .refreshToken(jwtUtil.generateRefreshToken(userId))
                .build();
    }
}
