package com.ssutudy.backend.auth.controller;

import com.ssutudy.backend.auth.request.SignInRequest;
import com.ssutudy.backend.auth.request.SignUpRequest;
import com.ssutudy.backend.auth.response.TokenResponse;
import com.ssutudy.backend.auth.service.UserService;
import com.ssutudy.backend.core.jwt.JwtClaim;
import com.ssutudy.backend.core.jwt.JwtUtil;
import com.ssutudy.backend.domain.user.dto.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(
        @Valid @RequestBody SignUpRequest signUpRequest
    ) {
        UserDetail user = userService.signUp(signUpRequest);

        JwtClaim claim = new JwtClaim(user.getId());
        String accessToken = JwtUtil.generateAccessToken(claim);

        return new TokenResponse(accessToken, user);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse signIn(
        @Valid @RequestBody SignInRequest signInRequest
    ) {
        UserDetail user = userService.signIn(signInRequest);

        JwtClaim claim = new JwtClaim(user.getId());
        String accessToken = JwtUtil.generateAccessToken(claim);

        return new TokenResponse(accessToken, user);
    }
}
