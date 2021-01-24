package com.ssutudy.backend.auth.service;

import com.ssutudy.backend.auth.request.SignInRequest;
import com.ssutudy.backend.auth.request.SignUpRequest;
import com.ssutudy.backend.domain.user.dto.UserDetail;
import com.ssutudy.backend.domain.user.entity.User;
import com.ssutudy.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetail signUp(SignUpRequest signUpRequest) {
        boolean userAlreadyExists = userRepository.existsByEmail(signUpRequest.getId());
        if(userAlreadyExists) throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 해당 id를 사용하고 있는 사용자가 있습니다");

        User newUser = User.builder()
            .id(signUpRequest.getId())
            .name(signUpRequest.getName())
            .email(signUpRequest.getEmail())
            .major(signUpRequest.getMajor())
            .password(signUpRequest.getPassword())
            .build();

        newUser = userRepository.save(newUser);

        UserDetail userDetail = new UserDetail(newUser);
        return userDetail;
    }

    @Transactional(readOnly = true)
    public UserDetail signIn(SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserDetail userDetail = new UserDetail(optionalUser.get());
        return userDetail;
    }
}
