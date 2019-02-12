package com.phoneBook2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<?> ContactNotProvidedExceptionHandler(Throwable exception) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
  }
}
