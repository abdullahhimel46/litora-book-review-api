package com.litora.bookreview.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    /**
     * Secret key used to sign and verify HMAC-SHA algorithms.
     * Injected from 'jwt.secret' in application.yaml with a fallback default
     * (minimum 256-bit).
     */
    @Value("${jwt.secret:mySecretKeyForBookReviewApi12345678901234567890}")
    private String secret;

    /**
     * Token validity duration in milliseconds.
     * Injected from 'jwt.expiration' in application.yaml (defaults to 86,400,000 ms
     * / 24 hours).
     */
    @Value("${jwt.expiration:86400000}")
    private long expiration;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        Date now = new Date();

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) &&!isTokenExpired(token);
    }
}
