package com.phoneBook2.exceptions;

public class ContactNotProvidedException  extends ContactServiceExceptions{

  public ContactNotProvidedException(String message) {
    super(message);
  }

  public ContactNotProvidedException(String message, Throwable cause) {
    super(message, cause);
  }
}
