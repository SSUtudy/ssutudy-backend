package com.ssutudy.backend;

import com.ssutudy.backend.auth.dto.SignInRequest;
import com.ssutudy.backend.auth.dto.TokenResponse;
import com.ssutudy.backend.domain.user.entity.User;
import com.ssutudy.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignInTest extends ApiIntegrationTest {
    private static final String EMAIL = "test@email.com";
    private static final String PASSWORD = "password";
    private static final String NAME = "NAME";
    private static final String MAJOR = "MAJOR";

    private final UserRepository userRepository;

    @Autowired
    public SignInTest(@LocalServerPort int port, UserRepository userRepository) {
        super(port);
        this.userRepository = userRepository;
    }

    @Test
    void signInShouldReturnToken() {
        User user = User.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .name(NAME)
            .major(MAJOR)
            .build();

        userRepository.save(user);

        SignInRequest signInRequest = SignInRequest.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .build();

        RequestEntity<SignInRequest> request = RequestEntity.post(URI.create("/v1/auth/sign-in"))
            .body(signInRequest);

        ResponseEntity<TokenResponse> response = apiTestContext.getTestRestTemplate().exchange(request, TokenResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
