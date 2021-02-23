package com.ssutudy.backend.auth.exception;

import com.ssutudy.backend.core.exception.AppException;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(int status, String message) {
        super(status, message);
    }
}
