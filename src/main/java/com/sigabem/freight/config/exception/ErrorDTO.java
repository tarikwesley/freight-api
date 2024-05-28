package com.sigabem.freight.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDTO {
    private final String message;
    private final HttpStatus status;

    public ErrorDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
