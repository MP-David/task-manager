package com.david.task_manager.handler;

import com.david.task_manager.exception.BadRequest;
import com.david.task_manager.exception.BadRequestExecptionsDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<BadRequestExecptionsDetails> badRequest(BadRequest ex) {
        return new ResponseEntity<>(
                BadRequestExecptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developMessage(ex.getClass().getName())
                        .title("Bad Request")
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BadRequestExecptionsDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                BadRequestExecptionsDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title(ex.getClass().getName())
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value()).build()
                , HttpStatus.BAD_REQUEST);
    }

}
