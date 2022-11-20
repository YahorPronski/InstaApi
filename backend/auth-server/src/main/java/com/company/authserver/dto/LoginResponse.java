package com.company.authserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private final String tokenType = "Bearer";
    private String accessToken;
    private String refreshToken;

}
