package com.ssutudy.backend.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        ErrorResponse errorResponse = new ErrorResponse(ex, status, path);
        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }
}
