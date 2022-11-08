package com.company.instaapi.util;

import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Component
public class JwtUtil {
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.minutes}")
    private Integer jwtExpirationMinutes;

    public String generateToken(@NotNull String userId) {
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.add(Calendar.MINUTE, jwtExpirationMinutes);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expirationTime.getTime())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getTokenFromAuthHeader(String header) {
        if (StringUtils.isNotBlank(header) && header.startsWith(BEARER_TOKEN_PREFIX)) {
            return header.substring(BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }

    public String getUserIdFromToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            Claims claims = getTokenClaims(token);
            if (claims != null) {
                return claims.getSubject();
            }
        }
        return null;
    }

    private Claims getTokenClaims(@NotNull String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
