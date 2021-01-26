package com.ssutudy.backend.config.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@Getter
public class ErrorResponse {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(Exception ex, HttpStatus status, String path) {
        this.timestamp = new Date();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = ex.getMessage();
        this.path = path;
    }
}
