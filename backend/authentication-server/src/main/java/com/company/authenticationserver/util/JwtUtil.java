package com.company.authenticationserver.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Calendar;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    private final Integer accessTokenExpirationMinutes;
    private final Integer refreshTokenExpirationDays;

    public JwtUtil(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret,
            @Value("${token.expiration.access.minutes}") Integer accessTokenExpirationMinutes,
            @Value("${token.expiration.refresh.days}") Integer refreshTokenExpirationDays
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
        this.accessTokenExpirationMinutes = accessTokenExpirationMinutes;
        this.refreshTokenExpirationDays = refreshTokenExpirationDays;
    }

    public String generateAccessToken(String userId) {
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.add(Calendar.MINUTE, accessTokenExpirationMinutes);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expirationTime.getTime())
                .signWith(jwtAccessSecret)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        Calendar expirationTime = Calendar.getInstance();
        expirationTime.add(Calendar.DAY_OF_MONTH, refreshTokenExpirationDays);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expirationTime.getTime())
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(String token, SecretKey secret) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredEx) {
            log.error("Token expired", expiredEx);
        } catch (UnsupportedJwtException unsupportedEx) {
            log.error("Unsupported jwt", unsupportedEx);
        } catch (MalformedJwtException malformedEx) {
            log.error("Malformed jwt", malformedEx);
        } catch (Exception ex) {
            log.error("Invalid token", ex);
        }
        return false;
    }

    public Claims getAccessTokenClaims(String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshTokenClaims(String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(String token, SecretKey secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

