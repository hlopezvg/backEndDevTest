package com.test.backenddevtest.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {


  public ResourceNotFoundException(String message) {
    super("Resource "+ message + " Not found");
  }
}