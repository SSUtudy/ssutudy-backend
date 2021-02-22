package com.ssutudy.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenResponse {
    private String accessToken;
    private UserDetail user;
}
