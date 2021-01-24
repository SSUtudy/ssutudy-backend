package com.ssutudy.backend.auth.response;

import com.ssutudy.backend.domain.user.dto.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenResponse {
    private String accessToken;
    private UserDetail user;
}
