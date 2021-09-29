package com.spring.javainternship.csongorburu.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return new ErrorMessage(
        LocalDateTime.now(),
        ex.getMessage(),
        request.getDescription(false));
  }

  @ExceptionHandler(BadInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage badInputException(BadInputException ex, WebRequest request) {
    return new ErrorMessage(
        LocalDateTime.now(),
        ex.getMessage(),
        request.getDescription(false));
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
  public ErrorMessage illegalStateException(IllegalStateException ex, WebRequest request) {
    return new ErrorMessage(
        LocalDateTime.now(),
        ex.getMessage(),
        request.getDescription(false));
  }
}