package com.ssutudy.backend.auth.controller;

import com.ssutudy.backend.auth.dto.SignInRequest;
import com.ssutudy.backend.auth.dto.SignUpRequest;
import com.ssutudy.backend.auth.dto.TokenResponse;
import com.ssutudy.backend.auth.dto.UserDetail;
import com.ssutudy.backend.auth.service.AuthService;
import com.ssutudy.backend.core.jwt.JwtClaim;
import com.ssutudy.backend.core.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(
        @Valid @RequestBody SignUpRequest signUpRequest
    ) {
        UserDetail user = new UserDetail(authService.signUp(signUpRequest));

        JwtClaim claim = new JwtClaim(user.getId());
        String accessToken = JwtUtil.generateAccessToken(claim);

        return new TokenResponse(accessToken, user);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse signIn(
        @Valid @RequestBody SignInRequest signInRequest
    ) {
        UserDetail user = new UserDetail(authService.signIn(signInRequest));

        JwtClaim claim = new JwtClaim(user.getId());
        String accessToken = JwtUtil.generateAccessToken(claim);

        return new TokenResponse(accessToken, user);
    }
}
