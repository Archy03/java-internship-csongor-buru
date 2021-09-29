package com.spring.javainternship.csongorburu.exception;

public class BadInputException extends RuntimeException {

  private static final long serialVersionUID = 8581155397457573927L;

  public BadInputException(String message) {
    super(message);
  }
}
