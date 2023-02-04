package com.example.retail.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity processIlleaglArgumentException(IllegalArgumentException i){
        return new ResponseEntity(i.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity processRunTimeException(RuntimeException e){
        log.error("RuntimeException: ", e);
        return new ResponseEntity("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
