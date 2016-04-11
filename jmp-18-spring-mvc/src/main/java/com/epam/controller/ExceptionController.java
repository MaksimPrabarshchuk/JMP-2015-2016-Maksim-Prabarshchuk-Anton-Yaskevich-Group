package com.epam.controller;

import com.epam.exception.NoUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoUserException.class)
    public ResponseEntity handleAllException(NoUserException ex) {
        return new ResponseEntity<String>(ex.getErrMsg(), HttpStatus.NOT_FOUND);
    }
}
