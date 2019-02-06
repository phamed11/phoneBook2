package com.phoneBook2.exceptions;

public class ParamaterNotProvidedException extends ParameterExceptions {
  public ParamaterNotProvidedException(String message) {
    super(message);
  }

  public ParamaterNotProvidedException(String message, Throwable cause) {
    super(message, cause);
  }
}
