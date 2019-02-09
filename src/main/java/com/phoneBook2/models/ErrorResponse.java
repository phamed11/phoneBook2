package com.phoneBook2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
  private String errorResponse;

  public ErrorResponse(String errorResponse) {
    this.errorResponse = errorResponse;
  }
}
