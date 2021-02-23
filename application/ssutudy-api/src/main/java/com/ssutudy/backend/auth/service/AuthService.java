package com.ssutudy.backend.auth.service;

import com.ssutudy.backend.auth.dto.SignInRequest;
import com.ssutudy.backend.auth.dto.SignUpRequest;
import com.ssutudy.backend.auth.exception.UserAlreadyExistException;
import com.ssutudy.backend.auth.exception.UserNotFoundException;
import com.ssutudy.backend.domain.user.entity.User;
import com.ssutudy.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public User signUp(SignUpRequest signUpRequest) {
        boolean userAlreadyExists = userRepository.existsByEmail(signUpRequest.getEmail());
        if(userAlreadyExists) throw new UserAlreadyExistException(HttpStatus.CONFLICT.value(), "이미 해당 이메일를 사용하고 있는 사용자가 있습니다");

        User newUser = User.builder()
            .name(signUpRequest.getName())
            .email(signUpRequest.getEmail())
            .major(signUpRequest.getMajor())
            .password(signUpRequest.getPassword())
            .build();

        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public User signIn(SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if(optionalUser.isEmpty()) throw new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 사용자입니다");

        return optionalUser.get();
    }
}
