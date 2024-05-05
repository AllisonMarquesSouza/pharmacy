package com.system.remedios.handler;

import com.system.remedios.Exception.ExceptionDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice//all time than have an exception will come this class

public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public static ResponseEntity<ExceptionDetails> handlerBadRequestException(EntityNotFoundException bad) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception , Check the Documentation")
                        .details("Medicine not found")
                        .developerMessage(bad.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException notValid) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception , Check the Documentation")
                        .details("The name cannot be empty")
                        .developerMessage(notValid.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
