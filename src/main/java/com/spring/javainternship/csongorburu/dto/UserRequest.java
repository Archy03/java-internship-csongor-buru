package com.spring.javainternship.csongorburu.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserRequest {

  @NotBlank
  String firstName;
  @NotBlank
  String lastName;
  @Email
  String email;
  @NotBlank
  String password;
}