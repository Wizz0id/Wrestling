package com.example.Wrestling.service;

import com.example.Wrestling.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.access_token_expiration}")
    private long ACCESS_TOKEN_EXPIRATION;

    @Value("${security.jwt.refresh_token_expiration}")
    private long REFRESH_TOKEN_EXPIRATION;

    @Value("${secure.jwt.secret_key}")
    private String keyString;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(keyString.getBytes());
    }

    public String generateAccessToken(User user) {
        return generateToken(user, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, REFRESH_TOKEN_EXPIRATION);
    }

    private String generateToken(User user, long tokenExpiration) {
        Claims claims = (Claims) Jwts.claims().subject(user.getUsername());
        claims.put("id", user.getId());
        claims.put("roles", user.getAuthorities());
        claims.put("type", "access");

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration*1000))
                .signWith(key)
                .compact();
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    public boolean validate(String token) {
        return isTokenExpired(token);
    }
}
