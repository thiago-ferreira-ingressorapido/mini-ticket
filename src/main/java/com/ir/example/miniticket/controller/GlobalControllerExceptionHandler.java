package com.ir.example.miniticket.controller;

import com.ir.example.miniticket.exceptions.InvalidTicketException;
import com.ir.example.miniticket.exceptions.ResourceNotFoundException;
import com.ir.example.miniticket.model.ImmutableErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Class responsible to handle the exceptions throw by controllers
 * @author thiago-ferreira
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ImmutableErrorDetails errorDetails =  ImmutableErrorDetails.builder().
            timestamp(LocalDateTime.now()).
            message(ex.getMessage()).
            details(request.getDescription(false)).
            build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTicketException.class)
    public ResponseEntity<?> invalidTicketException(InvalidTicketException ex, WebRequest request) {
        ImmutableErrorDetails errorDetails =  ImmutableErrorDetails.builder().
            timestamp(LocalDateTime.now()).
            message(ex.getMessage()).
            details(request.getDescription(false)).
            build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
