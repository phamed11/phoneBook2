//package com.phoneBook2.controller;
//
//import com.phoneBook2.models.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//public class SpringRestExceptionHandler {
//
//  @ExceptionHandler(NoHandlerFoundException.class)
//  @ResponseStatus(HttpStatus.NOT_FOUND)
//  public @ResponseBody
//  ResponseEntity<ErrorResponse> handleNoMethodException(HttpServletRequest request,
//                                                        NoHandlerFoundException ex) {
//    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
//    errorResponse.setErrorResponse("resource not found with exception");
//    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//  }
//
//  @ExceptionHandler(Throwable.class)
//  public @ResponseBody ResponseEntity<ErrorResponse> handleDefaultException(Throwable ex) {
//    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
//    errorResponse.setErrorResponse("request has empty body or exception occured");
//    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//  }
//}