package com.ssutudy.backend.core.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException {
    protected int status;
    protected String message;
}
