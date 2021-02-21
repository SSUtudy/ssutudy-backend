package com.ssutudy.backend.auth.exception;

import com.ssutudy.backend.core.exception.AppException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends AppException {
    public UserAlreadyExistException(int status, String message) {
        super(status, message);
    }
}
