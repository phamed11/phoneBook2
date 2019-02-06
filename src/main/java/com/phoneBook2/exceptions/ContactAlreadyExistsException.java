package com.phoneBook2.exceptions;

public class ContactAlreadyExistsException extends ContactServiceExceptions {
  public ContactAlreadyExistsException(String message) {
    super(message);
  }

  public ContactAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
