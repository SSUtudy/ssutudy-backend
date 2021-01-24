package com.ssutudy.backend.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class JwtClaim {
    private String id;

    public Map<String, Object> generate() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);

        return claims;
    }
}
