package com.ssutudy.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Map;

public class JwtUtil {
    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateAccessToken(JwtClaim claim) {
        Map<String, Object> claimMap = claim.generate();

        String accessToken = Jwts.builder()
            .setClaims(claimMap)
            .signWith(secretKey)
            .compact();

        return accessToken;
    }
}
