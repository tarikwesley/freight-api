package com.sigabem.freight.config.exception;

import com.sigabem.freight.exception.ViaCepException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CostumerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericExceptionHandler(Exception e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ViaCepException.class)
    public ResponseEntity<Object> viaCepExceptionHandler(ViaCepException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
