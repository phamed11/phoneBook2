package com.phoneBook2.exceptions;

public class ContactServiceExceptions extends RuntimeException {

  public ContactServiceExceptions(String message) {
    super(message);
  }

  public ContactServiceExceptions(String message, Throwable cause) {
    super(message, cause);
  }
}
