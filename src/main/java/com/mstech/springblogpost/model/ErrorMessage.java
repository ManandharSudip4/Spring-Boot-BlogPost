package com.mstech.springblogpost.model;

import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // creates a default constructor i guess
public class ErrorMessage {

  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;

  public ErrorMessage(
    int statusCode,
    Date timestamp,
    String message,
    String description
  ) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }
}
