package com.example.Wrestling.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${secure.jwt.secret_key}")
    private String secret_key;
    public String generateToken(String username) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    public boolean isTokenExpired(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
}
