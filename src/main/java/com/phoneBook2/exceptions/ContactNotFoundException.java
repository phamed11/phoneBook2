package com.phoneBook2.exceptions;

public class ContactNotFoundException extends ContactServiceExceptions {
  public ContactNotFoundException(String message) {
    super(message);
  }

  public ContactNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
