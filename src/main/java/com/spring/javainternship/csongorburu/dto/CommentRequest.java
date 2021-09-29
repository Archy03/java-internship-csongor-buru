package com.spring.javainternship.csongorburu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CommentRequest {

  @NotBlank
  String text;
  @NotNull
  Long userId;
  @NotNull
  Long postId;
}