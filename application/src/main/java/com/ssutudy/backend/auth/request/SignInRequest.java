package com.ssutudy.backend.auth.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SignInRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
