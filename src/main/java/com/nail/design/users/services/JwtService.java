package com.nail.design.users.services;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nail.design.users.model.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time-ms}")
    private Long expirationTime;

    @Value("${security.jwt.issuer}")
    private String issuer;

    public JwtService() {
        // Empty Constructor
    }

    public String createJwtToken(UserEntity user) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        var key = Keys.hmacShaKeyFor(keyBytes);

        Instant now = Instant.now();
        Instant expirationInstant = now.plusMillis(expirationTime);

        return Jwts
                .builder().subject(user.getEmail())
                .issuedAt(Date.from(now))
                .issuer(issuer)
                .expiration(Date.from(expirationInstant))
                .signWith(key)
                .compact();
    }

    public Map<String, Object> getTokenClaims(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        var key = Keys.hmacShaKeyFor(keyBytes);

        try {
            var claims = Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            Date expDate = claims.getExpiration();
            Date currentDate = new Date();
            if (currentDate.before(expDate)) {
                return claims;
            }
        } catch (Exception ex) {
            // TODO: Colocar uma mensagem de erro descente aqui
        }
        return new HashMap<>();
    }
}
