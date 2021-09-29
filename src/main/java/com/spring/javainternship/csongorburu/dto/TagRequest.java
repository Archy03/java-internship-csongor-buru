package com.spring.javainternship.csongorburu.dto;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class TagRequest {

  @NotBlank
  String name;
}