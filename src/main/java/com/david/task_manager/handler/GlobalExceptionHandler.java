package com.david.task_manager.handler;

import com.david.task_manager.exception.BadRequestException;
import com.david.task_manager.exception.ExceptionsDetails;
import com.david.task_manager.exception.TaskNotFoundException;
import com.david.task_manager.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionsDetails> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionsDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title(ex.getClass().getName())
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value()).build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title(ex.getClass().getName())
                        .details(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value()).build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handleUserNotFoundException(TaskNotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title(ex.getClass().getName())
                        .details(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value()).build()
                , HttpStatus.NOT_FOUND);
    }

}


