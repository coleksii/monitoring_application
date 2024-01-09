package com.coleksii.monitoring.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -1964839652066902559L;

  public NotFoundException(String message) {
    super(message);
  }
}