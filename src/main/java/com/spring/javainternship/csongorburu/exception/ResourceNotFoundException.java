package com.spring.javainternship.csongorburu.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -2006245838493499322L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}