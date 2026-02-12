package com.dev.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

      @ExceptionHandler(RejectedExecutionException.class)

      public ResponseEntity<String> handleRejectedExecution(RejectedExecutionException e){

            return ResponseEntity
                      .status(HttpStatus.SERVICE_UNAVAILABLE)
                      .body("System Overloaded. Please try again later.");
      }

}
