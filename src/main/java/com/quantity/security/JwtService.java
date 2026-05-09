package com.quantity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpiration
                        )
                )
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        final String username =
                extractUsername(token);

        return username.equals(
                userDetails.getUsername()
        );
    }

    private <T> T extractClaim(
            String token,
            Function<Claims, T> resolver
    ) {

        Claims claims =
                Jwts.parser()
                        .verifyWith(
                                (javax.crypto.SecretKey)
                                        getSignInKey()
                        )
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

        return resolver.apply(claims);
    }

    private Key getSignInKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes()
        );
    }
}