package com.phoneBook2.exceptions;

public class JsonConverterExceptions extends RuntimeException {

  public JsonConverterExceptions(String message) {
    super(message);
  }

  public JsonConverterExceptions(String message, Throwable cause) {
    super(message, cause);
  }
}
