package com.phoneBook2.exceptions;

public class JsonConverterNoSuchFileFoundException extends JsonConverterExceptions {
  public JsonConverterNoSuchFileFoundException(String message) {
    super(message);
  }

  public JsonConverterNoSuchFileFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
