package com.phoneBook2.exceptions;

public class ParameterExceptions extends RuntimeException {

  public ParameterExceptions(String message) {
    super(message);
  }

  public ParameterExceptions(String message, Throwable cause) {
    super(message, cause);
  }
}
