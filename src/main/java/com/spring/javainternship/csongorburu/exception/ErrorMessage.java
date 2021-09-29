package com.spring.javainternship.csongorburu.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

  private LocalDateTime timestamp;
  private String message;
  private String description;
}