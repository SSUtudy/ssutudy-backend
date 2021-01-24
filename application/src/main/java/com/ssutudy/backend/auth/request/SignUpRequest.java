package com.ssutudy.backend.auth.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class SignUpRequest {
    @NotNull
    @Size(min=1, max=50)
    private String id;

    @NotNull
    @Size(min=1, max=50)
    private String major;

    @NotNull
    @Size(min=1, max=50)
    private String name;

    @NotNull
    @Size(min=64, max=64)
    private String password;

    @NotNull
    @Size(min=1, max=50)
    private String email;
}
