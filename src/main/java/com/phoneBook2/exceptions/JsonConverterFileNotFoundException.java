package com.phoneBook2.exceptions;

public class JsonConverterFileNotFoundException extends JsonConverterExceptions {
  public JsonConverterFileNotFoundException(String message) {
    super(message);
  }

  public JsonConverterFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
