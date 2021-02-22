package com.ssutudy.backend.auth.exception;

import com.ssutudy.backend.core.exception.AppException;

public class UserAlreadyExistException extends AppException {
    public UserAlreadyExistException(int status, String message) {
        super(status, message);
    }
}
