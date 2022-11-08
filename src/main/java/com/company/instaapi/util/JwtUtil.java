package com.company.instaapi.util;

import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class JwtUtil {
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String JWT_SECRET = "myJwtSecret";

    public static String generateToken(Long userId) {
        Date expirationDate = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static String getTokenFromAuthHeader(String header) {
        if (StringUtils.isNotBlank(header) && header.startsWith(BEARER_TOKEN_PREFIX)) {
            return header.substring(BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }

    public static String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
